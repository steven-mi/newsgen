package models

import org.scalatest.FunSuite
import org.tensorflow.{Tensor, Tensors}

class GPT2Test extends FunSuite {

  test("GPT2.predict") {
    val resourcePath = getClass.getResource("").getPath
    val gpt2 = new GPT2(resourcePath)
    val inputData = Tensors.create(Array(Array(1, 2, 3, 5, 34, 31, 2)))
    val prediction = gpt2.predict(inputData)
    assert(prediction.isInstanceOf[Tensor[Integer]])
    print(prediction.shape().mkString("Array(", ", ", ")"))
  }

}
