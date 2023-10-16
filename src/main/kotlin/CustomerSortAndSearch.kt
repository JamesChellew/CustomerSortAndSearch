import objects.Customer
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.lang.Exception

fun main(args: Array<String>) {
    var customerList = mutableListOf<Customer>() // Collection Object to store Customer objects
    // Adding Customers to list\
    // With the "customers.dat" file already saved,
    // it is possible for this code to be removed and load from file instead.
    customerList.add(Customer(1, "James Chellew", "jchellew@live.com.au", "0412123123"))
    customerList.add(Customer(2, "Mike Sanders", "MikeS@gmail.com", "0498987098"))
    customerList.add(Customer(3, "Rebecca Robberts", "RB@outlook.com", "0456567567"))
    customerList.add(Customer(4, "Henry Ford", "Ford@gmail.com", "0412456123"))
    customerList.add(Customer(5, "Albert Rutherford", "Atoms@galaxy.com.au", "0424242424"))
    displayAllCustomers(customerList)
    var input: String
    do {
        print(options())
        input = readln()
        when (input) {
            "1" -> displayAllCustomers(customerList)
            "2" -> sortCustomers(customerList)
            "3" -> searchByName(customerList)
            "4" -> searchByEmail(customerList)
            "5" -> searchByMobile(customerList)
            "6" -> saveCustomerList(customerList)
            "7" -> customerList = loadCustomerList()
            "8" -> println("\n\nExiting Program")
            else -> println("\nInvalid Option, Try Again\n")
        }
    } while (input != "8")
}

fun options() = """
    1. Display List of Customers
    2. Sort Customers by Name (and show)
    3. Search Customers by Name
    4. Search Customers by email
    5. Search Customers by mobile
    6. Save Customer List
    7. Load Customer List
    8. QUIT
    >>>
""".trimIndent()

fun displayAllCustomers(customerList: MutableList<Customer>) {
    // This will display the information of all the customers in the list
    for (x in customerList) {
        print("| ${x.id} | ${x.name} | ${x.email} | ${x.mobile} |\n")
    }
    println()
}

fun displaySingleCustomer(x: Customer) {
    // This will display the information of a single customer
    print("| ${x.id} | ${x.name} | ${x.email} | ${x.mobile} |\n")
}

fun sortCustomers(customerList: MutableList<Customer>) {
    // This will sort the list of customers by the name attribute and then displays it
    customerList.sortBy { it.name }
    println()
    displayAllCustomers(customerList)
}

fun searchByName(customerList: MutableList<Customer>) {
    // This allows the user to search for a customers by name where the input only needs to be part of the whole name
    print("\nEnter the name you would like to search for\n>>>")
    val searchTerm = readln()
    var match = false
    println()
    for (x in customerList) {
        if (x.name.contains(searchTerm, true)) {
            displaySingleCustomer(x)
            match = true
        }
    }
    if (!match){
        println("\nNo Matching Results\n")
    }
    println()
}

fun searchByEmail(customerList: MutableList<Customer>) {
    // This allows user to serach for customers by email where the input only needs to be a part of the whole email
    print("\nEnter the email you would like to search for\n>>>")
    val searchTerm = readln()
    var match = false
    println()
    for (x in customerList) {
        if (x.email.contains(searchTerm, true)) {
            displaySingleCustomer(x)
            match = true
        }
    }
    if (!match){
        println("\nNo Matching Results\n")
    }
    println()
}

fun searchByMobile(customerList: MutableList<Customer>) {
    // This allows the user to search for customers by mobile where the input only needs to be a part of the whole mobile
    print("\nEnter the mobile you would like to search for\n>>>")
    val searchTerm = readln()
    var match = false
    println()
    for (x in customerList) {
        if (x.mobile.contains(searchTerm, true)) {
            displaySingleCustomer(x)
            match = true
        }
    }
    if (!match){
        println("\nNo Matching Results\n")
    }
    println()
}

fun saveCustomerList(customerList: MutableList<Customer>) {
    // This will save a file "customers.dat" which saves the list object contains the customer objects
    // Note that the Customer object must be serialised for this to work
    try {
        val fileOutputStream = FileOutputStream("customers.dat")
        val objectOutputStream = ObjectOutputStream(fileOutputStream)
        objectOutputStream.writeObject(customerList)
        println("\nCurrent List Saved\n")
        fileOutputStream.close()
        objectOutputStream.close()
    } catch (e: Exception) {
        println("An Error has Occurred: $e \n")
    }

}

fun loadCustomerList(): MutableList<Customer> {
    // This will open and read the file saved as "customers.dat" and return the mutableList saved to it.
    // Like above, the Customer object must have been serialised for them to be saved in the first place.
    var customerList = mutableListOf<Customer>()
    try {
        val fileInputStream = FileInputStream("customers.dat")
        val objectInputStream = ObjectInputStream(fileInputStream)
        customerList = objectInputStream.readObject() as MutableList<Customer>
        println("\nSaved List Loaded\n")
        fileInputStream.close()
        objectInputStream.close()
    } catch (e: Exception) {
        println("An Error has Occurred: $e \n")
    }
    return customerList
}