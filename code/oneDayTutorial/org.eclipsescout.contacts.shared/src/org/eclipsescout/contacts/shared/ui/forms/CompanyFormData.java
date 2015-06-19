/**
 * 
 */
package org.eclipsescout.contacts.shared.ui.forms;

import java.util.Map;

import javax.annotation.Generated;

import org.eclipse.scout.rt.shared.data.form.AbstractFormData;
import org.eclipse.scout.rt.shared.data.form.ValidationRule;
import org.eclipse.scout.rt.shared.data.form.fields.AbstractValueFieldData;
import org.eclipse.scout.rt.shared.data.form.properties.AbstractPropertyData;

/**
 * <b>NOTE:</b><br>
 * This class is auto generated by the Scout SDK. No manual modifications recommended.
 * 
 * @generated
 */
@Generated(value = "org.eclipse.scout.sdk.workspace.dto.formdata.FormDataDtoUpdateOperation", comments = "This class is auto generated by the Scout SDK. No manual modifications recommended.")
public class CompanyFormData extends AbstractFormData {

  private static final long serialVersionUID = 1L;

  public CompanyFormData() {
  }

  /**
   * access method for property CompanyId.
   */
  public String getCompanyId() {
    return getCompanyIdProperty().getValue();
  }

  /**
   * access method for property CompanyId.
   */
  public void setCompanyId(String companyId) {
    getCompanyIdProperty().setValue(companyId);
  }

  public CompanyIdProperty getCompanyIdProperty() {
    return getPropertyByClass(CompanyIdProperty.class);
  }

  /**
   * access method for property CompanyNr.
   */
  public Long getCompanyNr() {
    return getCompanyNrProperty().getValue();
  }

  /**
   * access method for property CompanyNr.
   */
  public void setCompanyNr(Long companyNr) {
    getCompanyNrProperty().setValue(companyNr);
  }

  public CompanyNrProperty getCompanyNrProperty() {
    return getPropertyByClass(CompanyNrProperty.class);
  }

  public Location getLocation() {
    return getFieldByClass(Location.class);
  }

  public Name getName() {
    return getFieldByClass(Name.class);
  }

  public URL getURL() {
    return getFieldByClass(URL.class);
  }

  public static class CompanyIdProperty extends AbstractPropertyData<String> {

    private static final long serialVersionUID = 1L;

    public CompanyIdProperty() {
    }
  }

  public static class CompanyNrProperty extends AbstractPropertyData<Long> {

    private static final long serialVersionUID = 1L;

    public CompanyNrProperty() {
    }
  }

  public static class Location extends AbstractValueFieldData<String> {

    private static final long serialVersionUID = 1L;

    public Location() {
    }

    /**
     * list of derived validation rules.
     */
    @Override
    protected void initValidationRules(Map<String, Object> ruleMap) {
      super.initValidationRules(ruleMap);
      ruleMap.put(ValidationRule.MAX_LENGTH, 4000);
    }
  }

  public static class Name extends AbstractValueFieldData<String> {

    private static final long serialVersionUID = 1L;

    public Name() {
    }

    /**
     * list of derived validation rules.
     */
    @Override
    protected void initValidationRules(Map<String, Object> ruleMap) {
      super.initValidationRules(ruleMap);
      ruleMap.put(ValidationRule.MAX_LENGTH, 4000);
    }
  }

  public static class URL extends AbstractValueFieldData<String> {

    private static final long serialVersionUID = 1L;

    public URL() {
    }

    /**
     * list of derived validation rules.
     */
    @Override
    protected void initValidationRules(Map<String, Object> ruleMap) {
      super.initValidationRules(ruleMap);
      ruleMap.put(ValidationRule.MAX_LENGTH, 4000);
    }
  }
}