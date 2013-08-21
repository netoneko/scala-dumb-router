package dumb.router.impl

import javax.servlet.http.HttpServletRequest
import dumb.router.Request
import scala.collection.JavaConversions._

case class RealRequest(req: HttpServletRequest) extends Request {
  val uri = req getRequestURI

  val params = {
    val hash = new java.util.HashMap[String, Array[String]]
    hash.putAll(req.getParameterMap())
    mapAsScalaMap(hash)
  }
}