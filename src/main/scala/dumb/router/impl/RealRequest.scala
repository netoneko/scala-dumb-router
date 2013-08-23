package dumb.router.impl

import javax.servlet.http.HttpServletRequest
import dumb.router.Request

case class RealRequest(req: HttpServletRequest) extends Request {
  val uri = req.getRequestURI.replaceFirst(req.getServletPath, "")

  val params = collection.mutable.Map[String, Array[String]]()

  val immutableParams = req.getParameterMap()

  override def getParameterValues(key: String) = {
    val values = immutableParams.get(key)
    if (values == null) params(key) else values
  }
}