package it.unibo.encapsulation.interfaces;

public class StrictBankAccount implements BankAccount {
    
    /*
     * Aggiungere i seguenti campi:
     * - double balance: ammontare del conto
     * - int transactions: numero delle operazioni effettuate
     * - static double ATM_TRANSACTION_FEE = 1: costo delle operazioni via ATM
     */
    
    static final double ATM_TRANSACTION_FEE = 1;
    static final double TRANSACTION_FEE = .1;
    static final double FIXED_COST = 5;
    private final int id;
    private double balance;
    private int transactions;
    
    /*
    * Creare un costruttore pubblico che prenda in ingresso un intero (ossia l'id
    * dell'utente) ed un double (ossia, l'ammontare iniziale del conto corrente).
    */
    public StrictBankAccount(final int id, final double balance) {
        this.id = id;
        this.balance = balance;
    }
    
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setTransactions(int transactions) {
        this.transactions = transactions;
    }

    /*
    * Si aggiungano selettori per:
     * - ottenere l'id utente del possessore del conto
     * - ottenere il numero di transazioni effettuate
     * - ottenere l'ammontare corrente del conto.
     */
    public int getid() {
        return this.id;
    }

    public double getBalance() {
        return this.balance;
    }

    public int getTransactionsCount() {
        return this.transactions;
    }

    public boolean idCorresponds(final int id) {
        return this.getid() == id;
    }

    public boolean canWithdraw(final double amount) {
        return (this.getBalance() - amount) > 0;
    }

    public void deposit(final int id, final double amount) {
        /*
         * Incrementa il numero di transazioni e aggiunge amount al totale del
         * conto Nota: il deposito va a buon fine solo se l'id utente
         * corrisponde
         */

        if (idCorresponds(id)) {
            setBalance(getBalance() + amount);
            setTransactions(getTransactionsCount() + 1);
        }
    }

    public void withdraw(final int id, final double amount) {
        /*
         * Incrementa il numero di transazioni e rimuove amount al totale del
         * conto. Note: - Il conto puo' andare in rosso (ammontare negativo) -
         * Il prelievo va a buon fine solo se l'id utente corrisponde
         */
        
        if (idCorresponds(id) && canWithdraw(amount)) {
            setBalance(getBalance() - amount);
            setTransactions(getTransactionsCount() + 1);
        }
    }

    public void depositFromATM(final int id, final double amount) {
        /*
         * Incrementa il numero di transazioni e aggiunge amount al totale del
         * conto detraendo le spese (costante ATM_TRANSACTION_FEE) relative
         * all'uso dell'ATM (bancomat) Nota: il deposito va a buon fine solo se
         * l'id utente corrisponde
         */

        deposit(id, amount - ATM_TRANSACTION_FEE);
    }

    public void withdrawFromATM(final int id, final double amount) {
        /*
         * Incrementa il numero di transazioni e rimuove amount + le spese
         * (costante ATM_TRANSACTION_FEE) relative all'uso dell'ATM (bancomat)
         * al totale del conto. Note: - Il conto puo' andare in rosso (ammontare
         * negativo) - Il prelievo va a buon fine solo se l'id utente
         * corrisponde
         */

        withdraw(id, amount + ATM_TRANSACTION_FEE);
    }

    public void chargeManagementFees(final int id) {
        /*
         * Riduce il bilancio del conto di un ammontare pari alle spese di gestione
         */
        if (idCorresponds(id)) {
            setBalance(getBalance() - FIXED_COST - (TRANSACTION_FEE * getTransactionsCount()));
        }
    }

    public String toString() {
        return "StrictBankAccount [id=" + this.getid() + ",balance=" + this.getBalance() + ",transactionCount=" + this.getTransactionsCount() + "];";
    }
}
