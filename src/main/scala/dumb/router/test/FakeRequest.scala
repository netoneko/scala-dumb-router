package dumb.router.test

import javax.servlet.http.HttpServletRequest
import dumb.router.Request
import dumb.router.util.NotImplementedException

class FakeRequest(uriString: String) extends Request {
  override def req: HttpServletRequest = throw new NotImplementedException

  override def uri = uriString
}

