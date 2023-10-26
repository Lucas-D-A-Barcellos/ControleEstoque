
package DTO;

/**
 *
 * @author Lucas Daniel
 */
public class EstoqueDTO { // criação dos métodos de encapsulamento get e set das variaveis utilizadas

    /**
     * @return the sel_peca
     */
    public String getSel_peca() {
        return sel_peca;
    }

    /**
     * @param sel_peca the sel_peca to set
     */
    public void setSel_peca(String sel_peca) {
        this.sel_peca = sel_peca;
    }

    /**
     * @return the tipo_pecas
     */
    public String getTipo_pecas() {
        return tipo_pecas;
    }

    /**
     * @param tipo_pecas the tipo_pecas to set
     */
    public void setTipo_pecas(String tipo_pecas) {
        this.tipo_pecas = tipo_pecas;
    }

    /**
     * @return the num_pecas
     */
    public int getNum_pecas() {
        return num_pecas;
    }

    /**
     * @param num_pecas the num_pecas to set
     */
    public void setNum_pecas(int num_pecas) {
        this.num_pecas = num_pecas;
    }
    
    /**
     * @return the sel_pecas
     */
 
    
    private String tipo_pecas;
    private int num_pecas;
    private String sel_peca;
}
