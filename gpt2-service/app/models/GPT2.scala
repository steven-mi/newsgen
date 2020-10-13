package models

import java.nio.file.Paths

import org.tensorflow.{SavedModelBundle, Session, Tensor, Tensors}
import play.api.libs.json.Json

class GPT2(var modelPath: String) {
  var loadedModel: Session = SavedModelBundle.load(modelPath, "serve").session()

  def predict(inputData: Tensor[Integer]) = loadedModel.runner()
    .feed("Placeholder:0", inputData)
    .fetch("sample_sequence/while/Exit_3:0")
    .run().get(0)


}