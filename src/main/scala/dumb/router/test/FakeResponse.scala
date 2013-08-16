package dumb.router.test

import javax.servlet.http.HttpServletResponse
import dumb.router.Response
import dumb.router.util.NotImplementedException

class FakeResponse extends Response {
  var body: String = null
  var status: Int = -1
  var contentType: String = null
  var characterEncoding: String = null

  override def res: HttpServletResponse = throw new NotImplementedException

  override def write(data: Any) = {
    body = data.toString
  }

  override def setStatus(value: Int) = {
    status = value
  }

  override def code = status

  override def setCharacterEncoding(value: String) = {
    characterEncoding = value
  }

  override def setContentType(value: String) = {
    contentType = value
  }
}