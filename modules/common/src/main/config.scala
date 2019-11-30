package lila.common

import io.methvin.play.autoconfig._
import play.api.ConfigLoader

object config {

  case class CollName(value: String) extends AnyVal with StringValue

  case class Secret(value: String) extends AnyVal {
    override def toString = "Secret(****)"
  }

  case class BaseUrl(value: String) extends AnyVal with StringValue

  case class AppPath(value: String) extends AnyVal with StringValue

  case class MaxPerPage(value: Int) extends AnyVal with IntValue

  case class MaxPerSecond(value: Int) extends AnyVal with IntValue

  case class NetConfig(
      domain: String,
      protocol: String,
      @ConfigName("base_url") baseUrl: BaseUrl,
      email: EmailAddress
  )

  implicit val maxPerPageLoader = intLoader(MaxPerPage.apply)
  implicit val maxPerSecondLoader = intLoader(MaxPerSecond.apply)
  implicit val collNameLoader = strLoader(CollName.apply)
  implicit val secretLoader = strLoader(Secret.apply)
  implicit val baseUrlLoader = strLoader(BaseUrl.apply)
  implicit val emailAddressLoader = strLoader(EmailAddress.apply)
  implicit val appPathLoader = strLoader(AppPath.apply)
  implicit val netLoader = AutoConfig.loader[NetConfig]

  def strLoader[A](f: String => A): ConfigLoader[A] = ConfigLoader(_.getString) map f
  def intLoader[A](f: Int => A): ConfigLoader[A] = ConfigLoader(_.getInt) map f
}