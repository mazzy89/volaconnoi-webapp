
package it.volaconnoi.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Mazzy
 */
@Embeddable
public class PhoneNumber implements Serializable
{
    @Column(name = "TYPE")
    private String type;
    @Column(name = "AREA_CODE")
    private String area_code;
    @Column(name = "PHONE_NUMBER")
    private String phone_number;

    public PhoneNumber()
    {
        
    }
    
    public PhoneNumber(String type, String area_code, String phone_number)
    {
        this.type = type;
        this.area_code = area_code;
        this.phone_number = phone_number;
    }
    
    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }
    
    public String getArea_code()
    {
        return area_code;
    }

    public void setArea_code(String area_code)
    {
        this.area_code = area_code;
    }

    public String getPhone_number()
    {
        return phone_number;
    }

    public void setPhone_number(String phone_number)
    {
        this.phone_number = phone_number;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.phone_number);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final PhoneNumber other = (PhoneNumber) obj;
        if (!Objects.equals(this.phone_number, other.phone_number))
        {
            return false;
        }
        return true;
    }
}