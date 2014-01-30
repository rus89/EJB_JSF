/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package validatori;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Rus
 */
@FacesValidator("validatorJmbg")
public class ValidatorJmbg implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            return;
        }

        String jmbg = String.valueOf(value);

        if (jmbg.isEmpty()) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Morate uneti JMBG.", null);
            throw new ValidatorException(msg);
        }

        if (jmbg.length() != 13) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "JMBG mora sadrzati 13 cifara.", null);
            throw new ValidatorException(msg);
        }

        for (int i = 0; i < jmbg.length(); i++) {
            char znak = jmbg.charAt(i);
            if (!Character.isDigit(znak)) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "JMBG mora sadrzati samo cifre.", null);
                throw new ValidatorException(msg);
            }
        }
    }
}
