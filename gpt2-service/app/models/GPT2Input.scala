package models

import play.api.libs.json._

case class GPT2Input(text: String)

object GPT2Input {
  implicit val format: OFormat[GPT2Input] = Json.format[GPT2Input]
}
