package code.setup

import code.model.Curry

object LoadDB {
  def load {

    Curry.bulkDelete_!!()

    var aCurry: Curry = Curry.create
    aCurry.name("Lamb Vindaloo")
    aCurry.description("A hot dish, made world famous by the Chefs of Goa. It has a Portugese influence. It is cooked with vinegar, capsicum and whole spices.")
    aCurry.picUrl("http://orders.littleindia.co.nz/media/catalog/product/cache/1/image/340x255/9df78eab33525d08d6e5fb8d27136e95/3/3/33_vindaloo.jpg")
    aCurry.save
    aCurry = Curry.create
    aCurry.name("Lamb or Beef Madras")
    aCurry.description("A favourite with those who enjoy their curry hot. A South Indian dish with onions, ginger, garlic and spices.")
    aCurry.picUrl("http://orders.littleindia.co.nz/media/catalog/product/cache/1/image/340x255/9df78eab33525d08d6e5fb8d27136e95/5/4/54_murgee_madras_chicken_lamb_.jpg")
    aCurry.save
    aCurry = Curry.create
    aCurry.name("Rogan Josh")
    aCurry.description("The master chefs of the Royal Mughal kitchens boasted about perfecting this dish. Lean lamb cooked with roasted and crushed spices.")
    aCurry.picUrl("http://orders.littleindia.co.nz/media/catalog/product/cache/1/image/340x255/9df78eab33525d08d6e5fb8d27136e95/6/4/64_rogan_josh.jpg")
    aCurry.save
    aCurry = Curry.create
    aCurry.name("Murg Mumtaz(Butter Chicken)")
    aCurry.description("A world famous Indian delicacy. Also known as butter chicken. Half cooked the tandoori way and finished the curry way, with crushed cashews, cream and spices.")
    aCurry.picUrl("http://orders.littleindia.co.nz/media/catalog/product/cache/1/image/340x255/9df78eab33525d08d6e5fb8d27136e95/3/4/34_murg_mumtaz.jpg")
    aCurry.save
    aCurry = Curry.create
    aCurry.name("Chicken Korma")
    aCurry.description("Traditionally a mild dish cooked in a creamy sauce made of almond paste, cream and spices.")
    aCurry.picUrl("http://orders.littleindia.co.nz/media/catalog/product/cache/1/image/340x255/9df78eab33525d08d6e5fb8d27136e95/6/5/65_lamb_korma.jpg")
    aCurry.save
    aCurry = Curry.create
    aCurry.name("Chicken Hara Masala.")
    aCurry.picUrl("http://orders.littleindia.co.nz/media/catalog/product/cache/1/image/340x255/9df78eab33525d08d6e5fb8d27136e95/6/2/62_chicken_lamb_prawn_saagwala.jpg")
    aCurry.save
    aCurry = Curry.create
    aCurry.name("Chicken Tikka Masala")
    aCurry.description("An internationally renowned dish. Boneless chicken cooked in a secret recipe of yoghurt, cream and spices.")
    aCurry.picUrl("http://orders.littleindia.co.nz/media/catalog/product/cache/1/image/340x255/9df78eab33525d08d6e5fb8d27136e95/6/3/63_chicken_tikka_masala.jpg")
    aCurry.save
    aCurry = Curry.create
    aCurry.name("Lamb/Chicken Biryani")
    aCurry.picUrl("http://orders.littleindia.co.nz/media/catalog/product/cache/1/image/340x255/9df78eab33525d08d6e5fb8d27136e95/8/5/85_biryani_chicken.jpg")
    aCurry.description("Basmati rice cooked with chicken, lamb or prawns. Infused with fresh spices and garnished with fresh coriander. A delicious wholesome meal served with raita, mixed pickle and papadoms.")
    aCurry.save

    // Vegetarian
    aCurry = Curry.create
    aCurry.name("Tarka Daal")
    aCurry.description("(Vegetarian) Yellow split lentils cooked with cumin seed, ginger, garlic, tomatoes and turmeric.")
    aCurry.picUrl("http://orders.littleindia.co.nz/media/catalog/product/cache/1/image/340x255/9df78eab33525d08d6e5fb8d27136e95/7/8/78_tarka_daal.jpg")
    aCurry.save
    aCurry = Curry.create
    aCurry.name("Bombay Aaloo")
    aCurry.description("(Vegetarian) Diced potatoes cooked with cumin seeds and spices. This is a dry dish.")
    aCurry.picUrl("http://orders.littleindia.co.nz/media/catalog/product/cache/1/image/340x255/9df78eab33525d08d6e5fb8d27136e95/7/9/79_bombay_aloo.jpg")
    aCurry.save
    aCurry = Curry.create
    aCurry.name("Ganga Jamuna Subzi")
    aCurry.description("(Vegetarian) Fresh seasonal vegetables cooked with cumin seeds, turmeric, chopped tomatoes and spices.")
    aCurry.picUrl("http://orders.littleindia.co.nz/media/catalog/product/cache/1/image/340x255/9df78eab33525d08d6e5fb8d27136e95/7/4/74_ganga_jamuna_subzi.jpg")
    aCurry.save
    aCurry = Curry.create
    aCurry.name("Palak Paneer")
    aCurry.description("(Vegetarian) Cubes of home made Indian cottage cheese cooked with spinach and spices.")
    aCurry.picUrl("http://orders.littleindia.co.nz/media/catalog/product/cache/1/image/340x255/9df78eab33525d08d6e5fb8d27136e95/4/9/49_palak_paneer.jpg")
    aCurry.save
    aCurry = Curry.create
    aCurry.name("Daal Makhani")
    aCurry.description("(Vegetarian) Black lentils and kidney beans cooked with ghee and spices.")
    aCurry.picUrl("http://orders.littleindia.co.nz/media/catalog/product/cache/1/image/340x255/9df78eab33525d08d6e5fb8d27136e95/8/0/80_daal_makhani.jpg")
    aCurry.save
    aCurry = Curry.create
    aCurry.name("Malai Kofta")
    aCurry.description("(Vegetarian) Home made Indian cottage cheese, potatoes, nuts and spices mixed together and then finished in hot oil and served with a creamy gravy made of crushed cashews and spices.")
    aCurry.picUrl("http://orders.littleindia.co.nz/media/catalog/product/cache/1/image/340x255/9df78eab33525d08d6e5fb8d27136e95/7/3/73_malai_kofta.jpg")
    aCurry.save
    aCurry = Curry.create
    aCurry.name("Vegetable Biryani")
    aCurry.description("(Vegetarian) Basmati rice cooked with mixed seasonal vegetables. Infused with freshly ground spices and garnished with coriander. A delicious wholesome meal served with raita, mixed pickle and papadoms.")    
    aCurry.picUrl("http://orders.littleindia.co.nz/media/catalog/product/cache/1/image/340x255/9df78eab33525d08d6e5fb8d27136e95/9/2/92_biryani_vege.jpg")
    aCurry.save


  }

}



