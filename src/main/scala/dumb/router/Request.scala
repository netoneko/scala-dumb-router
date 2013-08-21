package dumb.router

import javax.servlet.http.HttpServletRequest

trait Request {
  def req: HttpServletRequest

  def uri = req getRequestURI

  def params = req.getParameterMap.asInstanceOf[scala.collection.mutable.Map[String, Array[String]]]

  def getParameter(key: String) = req.getParameter(key)

  def getParameterValues(key: String) = req.getParameterValues(key)
}