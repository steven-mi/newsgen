package models

import java.io.FileInputStream
import java.nio.file.Paths

import play.api.libs.json._

import sys.process._
import scala.util.matching.Regex

class Encoder(var resourcePath: String, var modelPath: String) {
  val encoderFilePath: String = Paths.get(modelPath, "encoder.json").toString
  val encoderFileStream = new FileInputStream(encoderFilePath)

  //var encoder: Map[String, Int] = Json.parse(encoderFileStream).as[Map[String, Int]]
  //var decoder: Map[Int, String] = encoder.map(_.swap)

  def encode(text: String): Array[Int] = ("python3 " +
    Paths.get(resourcePath, "encoder.py").toString + " " +
    modelPath + " " + text !!).split(",").map(_.trim().toInt)

  def decode(text: Array[Int]): String = "python3 " +
    Paths.get(resourcePath, "decoder.py").toString + " " +
    modelPath + " " + text.mkString("", " ", "") !!

}
