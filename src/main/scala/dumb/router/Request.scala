package dumb.router

trait Request {
  val uri: String
  val params: collection.mutable.Map[String, Array[String]]

  def getParameterValues(key: String) = params(key)

  def getParameter(key: String) = getParameterValues(key).head
}