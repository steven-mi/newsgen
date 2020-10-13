import java.io.File

object App {

  def main(args: Array[String]) {

    val resourcePath: String = getClass.getResource("trained_models").getPath

    print((new File(resourcePath))
      .listFiles
      .filter(_.isDirectory)
      .map(_.getName).mkString("Array(", ", ", ")"))

  }
}
