package code.setup

import code.model.Curry

object LoadDB {
  def load {
    var aCurry: Curry = Curry.create
    aCurry.name("Lamb Vindaloo")
    aCurry.description("A hot dish, made world famous by the Chefs of Goa. It has a Portugese influence. It is cooked with vinegar, capsicum and whole spices.")
    aCurry.picUrl("http://orders.littleindia.co.nz/media/catalog/product/cache/1/image/340x255/9df78eab33525d08d6e5fb8d27136e95/3/3/33_vindaloo.jpg")
    aCurry.save
    aCurry = Curry.create
    aCurry.name("Lamb or Beef Madras")
    aCurry.description("A favourite with those who enjoy their curry hot. A South Indian dish with onions, ginger, garlic and spices.")
    aCurry.save
    aCurry = Curry.create
    aCurry.name("Rogan Josh")
    aCurry.description("The master chefs of the Royal Mughal kitchens boasted about perfecting this dish. Lean lamb cooked with roasted and crushed spices.")
    aCurry.save
    aCurry = Curry.create
    aCurry.name("Murg Mumtaz(Butter Chicken)")
    aCurry.description("A world famous Indian delicacy. Also known as butter chicken. Half cooked the tandoori way and finished the curry way, with crushed cashews, cream and spices.")
    aCurry.save
    aCurry = Curry.create
    aCurry.name("Chicken Korma")
    aCurry.description("Traditionally a mild dish cooked in a creamy sauce made of almond paste, cream and spices.")
    aCurry.save
    aCurry = Curry.create
    aCurry.name("Chicken Hara Masala.")
    aCurry.save
    aCurry = Curry.create
    aCurry.name("Chicken Tikka Masala")
    aCurry.description("An internationally renowned dish. Boneless chicken cooked in a secret recipe of yoghurt, cream and spices.")

    // Vegetarian
    aCurry.save
    aCurry = Curry.create
    aCurry.name("Tarka Daal")
    aCurry.description("Yellow split lentils cooked with cumin seed, ginger, garlic, tomatoes and turmeric.")
    aCurry.save
    aCurry = Curry.create
    aCurry.name("Bombay Aaloo")
    aCurry.description("Diced potatoes cooked with cumin seeds and spices. This is a dry dish.")
    aCurry.save
    aCurry = Curry.create
    aCurry.name("Ganga Jamuna Subzi")
    aCurry.description("Fresh seasonal vegetables cooked with cumin seeds, turmeric, chopped tomatoes and spices.")
    aCurry.save
    aCurry = Curry.create
    aCurry.name("Palak Paneer")
    aCurry.description("Cubes of home made Indian cottage cheese cooked with spinach and spices.")
    aCurry.save
    aCurry = Curry.create
    aCurry.name("Daal Makhani")
    aCurry.description("Black lentils and kidney beans cooked with ghee and spices.")
    aCurry.save
    aCurry = Curry.create
    aCurry.name("Malai Kofta")
    aCurry.description("Home made Indian cottage cheese, potatoes, nuts and spices mixed together and then finished in hot oil and served with a creamy gravy made of crushed cashews and spices.")
    aCurry.save
    aCurry = Curry.create
    aCurry.name("Vegetable Biryani")
    aCurry.save
    aCurry = Curry.create
    aCurry.name("Lamb/Chicken Biryani")


  }

}



