package ticketmachine;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class TicketMachineTest {
	private static final int PRICE = 50; // Une constante

	private TicketMachine machine; // l'objet à tester

	@BeforeEach
	public void setUp() {
		machine = new TicketMachine(PRICE); // On initialise l'objet à tester
	}

	@Test
	// On vérifie que le prix affiché correspond au paramètre passé lors de l'initialisation
	// S1 : le prix affiché correspond à l’initialisation.
	void priceIsCorrectlyInitialized() {
		// Paramètres : valeur attendue, valeur effective, message si erreur
		assertEquals(PRICE, machine.getPrice(), "Initialisation incorrecte du prix");
	}

	@Test
	// S2 : la balance change quand on insère de l’argent
	void insertMoneyChangesBalance() {
		machine.insertMoney(10);
		machine.insertMoney(20);
                // Les montants ont été correctement additionnés  
		assertEquals(10 + 20, machine.getBalance(), "La balance n'est pas correctement mise à jour");              
	}

	@Test
	// S3 : on n’imprime pas leticket si le montant inséré est insuffisant
	void nImprimePasSiMontantInsuffisant(){
		machine.insertMoney(PRICE-1);
		assertFalse(machine.printTicket(), "Le ticket ne doit pas etre imprimé");
	}

    @Test
	// S4 :on imprime le ticket si le montant inséré est suffisant
	void nImprimeSiMontantsuffisant(){
		machine.insertMoney(PRICE+1);
		assertTrue(machine.printTicket(), "Le ticket doit étre imprimé");
	}

    @Test
	// S5 : Quand on imprime un ticket la balance est décrémentée du prix du ticket
	void balDecPrixTicket(){
		machine.insertMoney(PRICE+1);
		machine.printTicket();
		assertEquals(1, machine.getBalance(),"La balance doit étre décrementer");
	}

	@Test
	// S6 : le montant collecté est mis à jour quand on imprime un ticket (pas avant)
	void MontantCollectéMisaJourImprimeTicket(){
		machine.insertMoney(PRICE);
		machine.printTicket();
		assertEquals(0,machine.getTotal(),"La balance n'est pas mis à jour");
	}

	@Test
	// S7 : refund()rend correctement la monnaie
	void RefundCorrectementMonnaie(){
		machine.insertMoney(PRICE+1);
		machine.printTicket();
		assertEquals(1, machine.refund(),"Monnaie pas correctement refund");
	}

	@Test
	// S8: refund()remet la balance à zéro
	void RefundRemetaZero(){
		machine.insertMoney(PRICE+1);
		machine.printTicket();
		machine.refund();
		assertEquals(0, machine.getBalance(),"Balance pas mis à jour");
	}

	@Test
	// S9: on ne peut pas insérer un montant négatif
	void NePeutPasInsererMontantNegatif(){
		try{
			machine=new TicketMachine(-50);
			fail("montant non negatif");}
			catch(IllegalArgumentException e ){};
		}
	


    @Test
	//S10  on ne peut pas créer de machine qui délivre des tickets dont le prix est négatif
	public void NegativeTicket(){
		try{
			machine= new TicketMachine(-40);
			fail("montant non negatif");}
			catch(IllegalArgumentException  e){};
		}
	}
		



