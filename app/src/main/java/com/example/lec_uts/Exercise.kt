import java.io.Serializable

data class Exercise(
    var id: String = "",
    val name: String = "",
    val description: String = "",
    val calories: Double = 0.0 // Sesuai dengan field di Firestore
) : Serializable
