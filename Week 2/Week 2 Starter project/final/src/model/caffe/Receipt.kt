package model.caffe

data class Receipt(
  val id: String,
  val products: List<Product>,
  val totalPrice: Double,
  val customerId: String
)