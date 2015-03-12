This googlecode project comprises pragmatic solutions for identity propagation and data access control. Identity propagation allows a user identity to be preserved in distributed application architectures, regardless of where the identity information was created, for use during authorization and for auditing purposes (IBM, 2011). Data access control (or authorization rule execution), on the other side, is an authorization mechanism that enforce authorization rules, a type of business rule that restricts who is allowed to perform a certain action on which data (BRG, 2000).

Application architectures for database access can be divided into four types (Azevedo et al., 2010b; Leao et al., 2011a) depending on the application layers involved in the scenario, as illustrated in Figure 1 and described as follows. Identity propagation solutions should ideally address each scenario.

  * **Client application connects directly the database using an identifiable user (Figure 1.i):** the database user is the same as application client’s user which is the same as the user that is connect to the operating system.

  * **Client application connects directly the database using a non-identifiable user (Figure 1.i):** the user that executes the client application is different from the user connected to the operating system.

  * **Client application connects to and application server that access the database ((Figure 1.ii):** client application sends requests to an application server that connects to the database to execute CRUD (Create-Retrieve-Update-Delete) operations. The application server connects to the database using a general user.

  * **Client application connects to and application server that invokes a web service that access the database (Figure 1.iii):** many layers are considered in this scenario to propagate user identity, and the real user that is logged in the operating system where application client is running is far from the database.

<img src='http://pdac.googlecode.com/files/4cenarios.png' width='70%' height='80%' />

**Figure 1.** Identity propagation considering the four scenarios for database access

As long as identity propagation mechanisms are implemented (following one or a combination of the scenarios in Figure 1), the DBMS is aware of the user that is connected to the client application accessing DBMS. Authorization rules may then be applied into the DBMS. This Project solves the authorization rule execution problem by employing FARBAC, a flexible approach for Role-Based Access Control in Oracle DBMS (Azevedo et al., 2010a; Puntar et. al., 2010, 2011). The database used to run the tests followed TPC-H benchmark schema (TPC Council, 2008), consisting in a specification used by industry that simulates a decision support application.

Currently the Project comprises two different prototypes concerning the third scenario described above and one concerning the fourth scenario. Below, each prototype is briefly explained.


### PdacEJB ###
This prototype aims at attending a scenario where an application accesses an EJB (deployed in a Jboss application server) responsible for providing functionalities that consider accessing a database. The prototype is composed by 3 projects:

  * “ClienteVendas“ - the client application
  * ”EJBVendas” - the EJB deployed in the application server
  * “ControleAcesso” - a common library shared by both projects


### PdacSpring ###
This prototype concerns a scenario specification where the Spring framework(1) is applied to expose functionalities that are later accessed through HTTP requests. The prototype is composed by 3 projects:

  * “SpringRemoteModCliente” - the client application
  * “SpringRemoteModServer” - the functionality deployed in the application server and exposed through Spring’s HTTP Remote
  * “SpringRemoteModShared” - a common Project that implements classes and interfaces necessary to both projects


### PdacJaxWS ###
This prototype concerns the fourth specified scenario, where a simple client accesses a web service (Logic Service) that exposes a simple method to consult which is the most common type of priority among all orders done to a company. The Logic Service access a second web service (Data Service) in order to reach the database, a TPC-H schema protected by the FARBAC framework. In order to propagate the identity of the user making the original consult, the SOAP header of each request is manipulated by Handlers. The services that compose the prototype are developed through JAX-WS specification. This prototype is composed by 4 projects:

  * “C4ServicoLogica” - The first web service (Logic Service), which is actually used by the client application
  * “C4ServicoDados” - The second web service (Data Service), responsible for accessing the TPC-H schema on a Oracle (10gR3) database with the FARBAC framework activated.
  * “C4ModuleShared” - A simple project where the Handler object is implemented.
  * “TesteConsumidorDados” - The client implemented to test the architecture.


### PdacJaxRPC ###
Still concerning the fourth scenario, another prototype has been developed. The prototype is composed by two services and one client project (embracing two actual clients). While the two services have been developed through JAX-RPC specification (considering the Oracle Weblogic 10g as application server), the clients have been implemented as standalone applications through JavaSE, aided by TestNG framework. TestNG is used in order to simplify the implementation and make possible to verify how the solution handles parallel requests. The identity propagation is enabled by the use of Handlers, as in PdacJaxWS prototype. This time a mock object was used to simulate the access to a database. The prototype is composed by 3 projects:

  * “ServicoHelloNoPropRPC” - This project is implemented to serve as a control example. It does not consider identity propagation, being accessed by the client just to  exemplify how JAX-RPC services work.
  * “ServicoHelloPropRPC” - The second web service uses Handler technology to intercept an user identity supplied through SOAP's header. The user is extracted and consider when a method is invoked. The only method implemented is a simple "Hello World" method.
  * “ClientRPC” - Comprises the clients to test the architecture. It is composed by two actual clients, one that accesses the first service and one that accesses the second service. the one that uses ServicoHelloPropRPC makes use of a handler, like the one used in PdacJaxWS prototype, to inject the user identity in the SOAP request message header.


### Final Considerations ###

The prototypes provided by this Project were implemented in order to test the proposals in in a real environment of a large brazilian company. For further details, consider the technical reports and papers referenced below.

  * PdacEJB => Leao et al., 2011a, 2011c.
  * PacSpring => Leal et al., 2011b.
  * PdacJaxWS and PdacJaxRPC => Technical report and paper under production.
  * FARBAC => Azevedo et al., 2010a, 2010b; Puntar et al., 2010, 2011.


---


### Bibliography ###

(AZEVEDO et al., 2010a) Azevedo, L.G., Puntar, S., Thiago, R., Baião, F., Cappelli, C., 2010. A Flexible Framework for Applying Data Access Authorization Business Rules. In Proceedings of the 12th International Conference on Enterprise Information Systems (ICEIS 2010). Funchal, Madeira, Portugal , pp. 275-280.

(AZEVEDO et al., 2010b) AZEVEDO, L., PUNTAR, S., THIAGO, R.M., BAIÃO, F. Practical Evaluation of Information Authorization Funcionalities (Label Security and Virtual Private Database). Technical Report of Applied Informatics Department DIA/UNIRIO (RelaTe-DIA) RT-0002/2010, 2010. Available at: http://seer.unirio.br/index.php/monografiasppgi. (in Portuguese)

(BRG, 2000) BRG, 2000. Defining Business Rules ~ What Are They Really?. Business Rule Group. Available at:  http://www.businessrulesgroup.org/first_paper/BRG-whatisBR_3ed.pdf. Accessed on July, 2011.

(IBM, 2010) IBM, RACF Security Guide, IBM, 2010. Available at: http://publib.boulder.ibm.com/infocenter/cicsts/v3r2/topic/com.ibm.cics.ts.doc/pdf/dfht 5c00.pdf. Accessed on August, 31st 2011.

(LEAO et al., 2011a) Leão, F., Puntar, S., Azevedo, L. G., Cappelli, C., Baião, F., 2011a. Data Access Control in Information Systems using Identity Propagation and Role-Based Access Control Mechanisms. In: VII Brazilian Symposium in Information Systems (SBS 2011), Salvador, Brazil.

(LEAO et al., 2011b) Leão, F., Puntar, S., Azevedo, L. G., Cappelli, C., Baião, F., 2011b. Enforcing Authorization Rules in Information Systems using Identity Propagation and Role-Based Access Control Mechanisms. In: IADIS Applied Computing, Rio de Janeiro, Brazil. (to be published)

(LEAO et al., 2011c) LEAO, F., AZEVEDO, L. G., FARIA, F., BAIAO, F., CAPPELLI, C. Identity Propagation in Three-Tier Applications. Technical Report of Applied Informatics Department DIA/UNIRIO (RelaTe-DIA), RT-0007/2011, 2011c. Available at: http://seer.unirio.br/index.php/monografiasppgi. (in Portuguese)

(PUNTAR et al., 2010) PUNTAR, S., AZEVEDO, L., BAIÃO, F., CAPPELLI, C.  Permance Tests Comparing Flexible Model against Traditional VPD. Technical Report of Applied Informatics Department DIA/UNIRIO (RelaTe-DIA) RT-0013/2010, 2010. Available at: http://seer.unirio.br/index.php/monografiasppgi. (in Portuguese)

(PUNTAR et al., 2011) Puntar, S., Azevedo, L. G., Baião, F., Cappelli, C., 2011. Implementing Flexible and Efficient Authorization Business Rules in Information Systems. In: IADIS Applied Computing, Rio de Janeiro, Brazil. (to be published)

(TPC Council, 2008) TPCH 2008, TPC Benchmark H Standard Specification [Revision 2](https://code.google.com/p/pdac/source/detail?r=2).8.0. Transaction Processing Perfermance Council. Available at: http://www.tpc.org/tpch/spec/tpch2.8.0.pdf. Accessed on August, 8th 2011.

(1) http://www.springsource.org/documentation