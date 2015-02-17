/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pmh.hrm.persona.id;

/**
 *
 * @author pmarquez - 2015-02-09 19:05
 */
public class IdTypeRec {
    private int     idTypeCode;
    private String  idType;
    private boolean active;

    public IdTypeRec ( ) {
        this.idTypeCode = 0;
        this.idType     = "";
        this.active     = false;
    }

    /**
     * @return the idTypeCode
     */
    public int getIdTypeCode() {
        return idTypeCode;
    }

    /**
     * @param idTypeCode the idTypeCode to set
     */
    public void setIdTypeCode(int idTypeCode) {
        this.idTypeCode = idTypeCode;
    }

    /**
     * @return the idType
     */
    public String getIdType() {
        return idType;
    }

    /**
     * @param idType the idType to set
     */
    public void setIdType(String idType) {
        this.idType = idType;
    }

    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }
    
    
}
