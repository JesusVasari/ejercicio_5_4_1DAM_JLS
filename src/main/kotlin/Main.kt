import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory

// El siguiente fragmento lee el archivo Xml y lo transforma en un documento:
fun readXml(pathName: String): Document {
    val xmlFile = File(pathName)
    return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlFile)
}

fun obtenerAtributosEnMapKV(e: Element): MutableMap<String, String> {
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

class catologoLibrosXML {

    fun existeLibro(id: String): Boolean {
        return true
    }

    fun infoLibro( id: String) {
        val xlmFile: File = File("C:\\xml\\ejercicio")
        val xmlDoc: Document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xlmFile)
        xmlDoc.documentElement.normalize()
        val bookList: NodeList = xmlDoc.getElementsByTagName("book")

        for (i in 0..bookList.length - 1) {
            var bookNode: Node = bookList.item(i)

            if (bookNode.getNodeType() === Node.ELEMENT_NODE) {

                val elem = bookNode as Element
                val mMap = mutableMapOf<String, String>()
                for (j in 0..elem.attributes.length - 1) {
                    mMap.putIfAbsent(elem.attributes.item(j).nodeName, elem.attributes.item(j).nodeValue)
                }
                println("Current Book : ${bookNode.nodeName} - $mMap")

                println("Author: ${elem.getElementsByTagName("author").item(0).textContent}")
                println("Title: ${elem.getElementsByTagName("title").item(0).textContent}")
                println("Genre: ${elem.getElementsByTagName("genre").item(0).textContent}")
                println("Price: ${elem.getElementsByTagName("price").item(0).textContent}")
                println("publish_date: ${elem.getElementsByTagName("publish_date").item(0).textContent}")
                println("description: ${elem.getElementsByTagName("description").item(0).textContent}")
            }
        }


    }
}



fun main() {

    var info: catologoLibrosXML = catologoLibrosXML()
    info.infoLibro("bk102")

}