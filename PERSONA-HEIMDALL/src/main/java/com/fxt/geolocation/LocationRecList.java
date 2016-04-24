
package com.fxt.geolocation;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * LocationRecList.java<br/><br/>
 * Creation Date 2014-07-10 14:35<br/><br/>
 * <b>DESCRIPTION:</b><br/><br/>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br/>
 * Version Date: 2014-07-10 14:35<br/>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2014-07-10 14:35
 */
@XmlRootElement ( name = "location_list" )
@XmlAccessorType ( XmlAccessType.FIELD )
public class LocationRecList {
    @XmlElement ( name = "location" )
    private List<LocationRec> locationRecList;

    public LocationRecList ( ) {
        this.locationRecList = new ArrayList<> ( );
    }

    public List<LocationRec> getList ( ) {
        return locationRecList;
    }

    public void setList ( List<LocationRec> locationRecList ) {
        this.locationRecList = locationRecList;
    }

}
