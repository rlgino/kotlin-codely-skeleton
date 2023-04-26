package com.codely.dataclasses

class InMemoryCatList: CatRepository {
    private val cats = mutableMapOf<String, Cat>()
    override fun save(cat: Cat) {
        cats[cat.id.toString()] = cat
    }

    override fun listAll(): List<Cat> {
        val list = mutableListOf<Cat>()
        cats.toList().forEach{
            list.add(it.second)
        }
        return list
    }

}