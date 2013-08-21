package dumb.router.test

import dumb.router.Request

class FakeRequest(uriString: String, requestParams: scala.collection.mutable.Map[String, Array[String]] = scala.collection.mutable.Map.empty) extends Request {
  override val uri = uriString

  override val params = requestParams
}

