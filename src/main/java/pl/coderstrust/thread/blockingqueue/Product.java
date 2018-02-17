package pl.coderstrust.thread.blockingqueue;

 class Product {

  private String name;

  Product(String name) {
    this.name = name;
  }

   public String getName() {
     return name;
   }

   public void setName(String name) {
     this.name = name;
   }

   @Override
   public String toString() {
     return "Product{" +
         "name='" + name + '\'' +
         '}';
   }
 }