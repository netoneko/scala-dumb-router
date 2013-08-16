package dumb.router.impl

import javax.servlet.http.HttpServletRequest
import dumb.router.Request

case class RealRequest(req: HttpServletRequest) extends Request