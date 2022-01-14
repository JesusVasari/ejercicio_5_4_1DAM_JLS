import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory



class catalogoLibrosXML {

    init {
        val documento = readXml("C:\\xml\\ejercicio")
    }

    fun existeLibro(doc: Document, id: String): MutableList<Node> {
        val bookList: NodeList = doc.getElementsByTagName(id)
        val lista = mutableListOf<Node>()
        for (i in 0..bookList.length - 1)
            lista.add(bookList.item(i))
        return lista
    }

    private fun obtenerAtributosEnMapKV(e: Element): MutableMap<String, String> {
        val mMap = mutableMapOf<String, String>()
        for (j in 0..e.attributes.length - 1)
            mMap.putIfAbsent(e.attributes.item(j).nodeName, e.attributes.item(j).nodeValue)

        return mMap
    }

    fun obtenerListaNodosPorNombre(doc: Document, tagName: String): MutableList<Node> {
        val bookList: NodeList = doc.getElementsByTagName(tagName)
        val lista = mutableListOf<Node>()
        for (i in 0..bookList.length - 1)
            lista.add(bookList.item(i))
        return lista
    }

    fun listadoNombre() {
        val ruta = readXml("C:\\xml\\ejercicio")
        val lista = obtenerListaNodosPorNombre(ruta, "book")
        lista.forEach {
            if (it.getNodeType() === Node.ELEMENT_NODE) {
                val elem = it as Element
                val mMap = obtenerAtributosEnMapKV(it)
                println("`- Título: ${it.getElementsByTagName("title").item(0).textContent}")

            }
        }
    }

    private fun readXml(pathName: String): Document {
        val xmlFile = File(pathName)
        return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlFile)
    }

    fun infoLibro(id: String) {

        val ruta = readXml("C:\\xml\\ejercicio")

        val lista = obtenerListaNodosPorNombre(ruta, "book")

        lista.forEach {
            if (it.getNodeType() === Node.ELEMENT_NODE) {
                val elem = it as Element
                val mMap = obtenerAtributosEnMapKV(it)


            }
        }
    }
}


fun main() {

    var info: catologoLibrosXML = catologoLibrosXML()
   info.listadoNombre()




}