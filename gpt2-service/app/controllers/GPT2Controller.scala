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
  var modelIndex: Int = 0

  val modelNames: Array[String] = (new File(modelDir))
    .listFiles
    .filter(_.isDirectory)
    .map(_.getName)

  // default model is the first one
  var modelPath: String = Paths.get(modelDir, modelNames(modelIndex)).toString
  var encoder: Encoder = new Encoder(resourcePath = resourcePath, modelPath = modelPath)
  var gpt2: GPT2 = new GPT2(modelPath)

  def getModels: Action[AnyContent] = Action {
    Ok(Json.toJson(modelNames))
  }

  def getModel: Action[AnyContent] = Action {
    Ok(Json.toJson(modelNames(modelIndex))).as(JSON)
  }

  def setModel(id: Int): Action[AnyContent] = Action {
    modelIndex = id
    modelPath = Paths.get(modelDir, modelNames(modelIndex)).toString
    encoder = new Encoder(resourcePath, modelPath)
    gpt2 = new GPT2(modelPath)
    Ok(Json.toJson(modelNames(id)))
  }

  def predict: Action[JsValue] = Action(parse.json) { request =>
    val inputData = request.body.validate[GPT2Input]
    inputData.fold(
      errors => {
        BadRequest(Json.obj("message" -> JsError.toJson(errors)))
      },
      input => {
        val prediction = predict_helper(input.text)
        print(prediction)
        Ok(Json.obj(("prediction", prediction)))
      }
    )
  }

  def predict_dummy: Action[JsValue] = Action(parse.json) { request =>
    val inputData = request.body.validate[GPT2Input]
    inputData.fold(
      errors => {
        BadRequest(Json.obj("message" -> JsError.toJson(errors)))
      },
      input => {
        val prediction = "prediction_dummy"
        print(prediction)
        Ok(Json.obj(("prediction", prediction)))
      }
    )
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
