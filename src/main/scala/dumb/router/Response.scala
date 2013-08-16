package dumb.router

import javax.servlet.http.HttpServletResponse
import sun.reflect.generics.reflectiveObjects.NotImplementedException

trait Response {
  def res: HttpServletResponse

  def setContentType(value: String) = res setContentType value

  def setCharacterEncoding(value: String) = res setCharacterEncoding value

  def write(data: Any) = res.getWriter write data.toString

  def setStatus(i: Int) = res setStatus i

  def code = res getStatus
}

case class RealResponse(res: HttpServletResponse) extends Response

class FakeResponse extends Response {
  var body: String = null
  var status: Int = 0
  var contentType: String = null
  var characterEncoding: String = null

  override def res: HttpServletResponse = throw new NotImplementedException

  override def write(data: Any) = {
    body = data.toString
  }

  override def setStatus(i: Int) = {
    status = i
  }

  override def code = status

  override def setCharacterEncoding(value: String) = {
    characterEncoding = value
  }

  override def setContentType(value: String) = {
    contentType = value
  }
}