package dumb.router.test

import javax.servlet.http.HttpServletRequest
import dumb.router.Request
import dumb.router.util.NotImplementedException

class FakeRequest(uriString: String, requestParams: scala.collection.mutable.Map[String, Array[String]] = scala.collection.mutable.Map.empty) extends Request {
  override def req: HttpServletRequest = throw new NotImplementedException

  override def uri = uriString

  override def params = requestParams

  override def getParameter(key: String) = requestParams(key).head

  override def getParameterValues(key: String) = requestParams(key)
}

