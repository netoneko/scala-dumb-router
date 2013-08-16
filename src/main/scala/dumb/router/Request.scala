package dumb.router

import javax.servlet.http.HttpServletRequest
import sun.reflect.generics.reflectiveObjects.NotImplementedException

trait Request {
  def req: HttpServletRequest

  def uri = req getRequestURI
}

case class RealRequest(req: HttpServletRequest) extends Request

class FakeRequest(uriString: String) extends Request {
  override def req:HttpServletRequest = throw new NotImplementedException

  override def uri = uriString
}