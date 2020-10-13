package models

import java.nio.file.Paths

import org.scalatest.FunSuite

class EncoderTest extends FunSuite {

  test("Encoder.encodeText.example1") {
    val resourcePath = getClass.getResource("").getPath
    val modelPath = Paths.get(resourcePath, "trained_models/tfmodel").toString
    val encoder = new Encoder(resourcePath, modelPath)
    val msg = "Hi I just met you, and you are crazy"
    val encoded_msg = encoder.encode(msg)
    val expected_msg = Array(39, 72, 220, 40, 1353, 1251, 293, 11, 295, 293, 448, 1843, 89, 88)
    assert(encoded_msg sameElements expected_msg)
  }
  test("Encoder.encodeText.example2") {
    val resourcePath = getClass.getResource("").getPath
    val modelPath = Paths.get(resourcePath, "trained_models/tfmodel").toString
    val encoder = new Encoder(resourcePath, modelPath)
    val msg = "Thomas arbeitet als Programmierer und führt nebenbei unter dem Pseudonym Neo Jobs als professioneller " +
      "Hacker aus. Ihn beschleicht das Gefühl, dass etwas Unvorstellbares und Geheimnisvolles sein Leben lenkt. Das Gefühl wird zur Gewissheit, als die Hackerin Trinity ihm den Anführer Morpheus vorstellt."
    val encoded_msg = encoder.encode(msg)
    val expected_msg = Array(51, 71, 300, 311, 1785, 314, 685, 220, 47, 360, 994, 1908, 519, 257,
      405, 2550, 83, 2973, 1466, 894, 727, 220, 47, 325, 762, 1174, 76, 220, 45, 68, 78, 220, 41,
      78, 2064, 685, 3557, 363, 472, 257, 220, 39, 688, 257, 642, 13, 220, 40, 855, 2389, 308, 681,
      678, 220, 38, 68, 2922, 1191, 11, 1194, 1662, 311, 220, 52, 77, 2240, 1565, 1364, 272, 405, 220,
      38, 68, 431, 328, 2759, 85, 725, 272, 985, 220, 43, 68, 755, 284, 260, 646, 13, 220, 35, 311, 220,
      38, 68, 2922, 1191, 954, 1131, 220, 38, 68, 86, 790, 1388, 11, 685, 399, 220, 39, 688, 257, 262, 220,
      51, 81, 262, 627, 2722, 555, 220, 32, 77, 2470, 257, 220, 44, 271, 4406, 339, 870, 1565, 83, 13)
    assert(encoded_msg sameElements expected_msg)
  }

  test("Encoder.decodeText.example1") {
    val resourcePath = getClass.getResource("").getPath
    val modelPath = Paths.get(resourcePath, "trained_models/tfmodel").toString
    val encoder = new Encoder(resourcePath, modelPath)
    val msg = Array(39, 72, 220, 40, 1353, 1251, 293, 11, 295, 293, 448, 1843, 89, 88)

    val encoded_msg = encoder.decode(msg)
    val expected_msg = "Hi I just met you, and you are crazy"
    assert(encoded_msg.compareTo(expected_msg) == 1)
  }
}
