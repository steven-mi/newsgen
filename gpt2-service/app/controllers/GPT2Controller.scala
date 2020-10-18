package controllers

import java.io.File
import java.nio.file.Paths

import models.{Encoder, GPT2, GPT2Input}
import org.tensorflow.Tensors
import javax.inject._
import play.Environment
import play.api.libs.json._
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

class GPT2Controller @Inject()(implicit ec: ExecutionContext,
                               val controllerComponents: ControllerComponents,
                               val env: Environment,
                              ) extends BaseController {

  val resourcePath: String = env.classLoader.getResource("").getPath
  val modelDir: String = Paths.get(resourcePath, "trained_models").toString

  val modelNames: Array[String] = (new File(modelDir))
    .listFiles
    .filter(_.isDirectory)
    .map(_.getName)

  var modelName: String = modelNames(0)

  // default model is the first one
  var modelPath: String = Paths.get(modelDir, modelName).toString
  var encoder: Encoder = new Encoder(resourcePath = resourcePath, modelPath = modelPath)
  var gpt2: GPT2 = new GPT2(modelPath)

  def getModels: Action[AnyContent] = Action {
    Ok(modelNames.mkString(","))
  }

  def getModel: Action[AnyContent] = Action {
    Ok(modelName)
  }

  def setModel(modelName: String): Action[AnyContent] = Action {
    if (modelNames.contains(modelName)) {
      modelPath = Paths.get(modelDir, modelName).toString
      encoder = new Encoder(resourcePath, modelPath)
      gpt2 = new GPT2(modelPath)
      Ok(modelName)
    } else {
      BadRequest(modelName + " does not exist")
    }
  }

  def predict: Action[AnyContent] = Action { request: Request[AnyContent] =>
    val textBody = request.body.asText
    textBody
      .map { text =>
        val prediction = predict_helper(text)
        Ok(prediction)
      }
      .getOrElse {
        BadRequest("Message is empty!")
      }
  }

  def predict_dummy: Action[AnyContent] = Action { request: Request[AnyContent] =>
    val textBody = request.body.asText
    textBody
      .map { text =>
        Ok(text)
      }
      .getOrElse {
        BadRequest("Message is empty!")
      }
  }

  def predict_helper(text: String): String = {
    val encoded_msg = encoder.encode(text)
    val inputData = Tensors.create(Array(encoded_msg))
    val prediction_tensor = gpt2.predict(inputData)

    val prediction_array = Array.ofDim[Int](1, prediction_tensor.shape()(1).toInt)
    prediction_tensor.copyTo(prediction_array)
    encoder.decode(prediction_array(0))
  }

}
