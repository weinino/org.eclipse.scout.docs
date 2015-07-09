/*******************************************************************************
 * Copyright (c) 2015 BSI Business Systems Integration AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Distribution License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/org/documents/edl-v10.html
 *
 * Contributors:
 *     BSI Business Systems Integration AG - initial API and implementation
 ******************************************************************************/
package org.eclipsescout.demo.widgets.client.ui.forms;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.exception.VetoException;
import org.eclipse.scout.commons.html.HTML;
import org.eclipse.scout.commons.html.IHtmlContent;
import org.eclipse.scout.commons.logger.IScoutLogger;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCloseButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractLinkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.longfield.AbstractLongField;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.client.ui.messagebox.IMessageBox;
import org.eclipse.scout.rt.client.ui.messagebox.MessageBoxes;
import org.eclipse.scout.rt.platform.util.NumberUtility;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;
import org.eclipsescout.demo.widgets.client.services.lookup.AnswerOptionsLookupCall;
import org.eclipsescout.demo.widgets.client.services.lookup.IconIdLookupCall;
import org.eclipsescout.demo.widgets.client.ui.forms.MessageBoxForm.MainBox.CloseButton;
import org.eclipsescout.demo.widgets.client.ui.forms.MessageBoxForm.MainBox.ConfigurationBox;
import org.eclipsescout.demo.widgets.client.ui.forms.MessageBoxForm.MainBox.ConfigurationBox.AutoCloseMillisField;
import org.eclipsescout.demo.widgets.client.ui.forms.MessageBoxForm.MainBox.ConfigurationBox.BodyField;
import org.eclipsescout.demo.widgets.client.ui.forms.MessageBoxForm.MainBox.ConfigurationBox.CancelButtonTextField;
import org.eclipsescout.demo.widgets.client.ui.forms.MessageBoxForm.MainBox.ConfigurationBox.DefaultReturnValueField;
import org.eclipsescout.demo.widgets.client.ui.forms.MessageBoxForm.MainBox.ConfigurationBox.HeaderField;
import org.eclipsescout.demo.widgets.client.ui.forms.MessageBoxForm.MainBox.ConfigurationBox.HiddenTextContentField;
import org.eclipsescout.demo.widgets.client.ui.forms.MessageBoxForm.MainBox.ConfigurationBox.HtmlField;
import org.eclipsescout.demo.widgets.client.ui.forms.MessageBoxForm.MainBox.ConfigurationBox.IconIdField;
import org.eclipsescout.demo.widgets.client.ui.forms.MessageBoxForm.MainBox.ConfigurationBox.MessageBoxConfiguredButton;
import org.eclipsescout.demo.widgets.client.ui.forms.MessageBoxForm.MainBox.ConfigurationBox.NoButtonTextField;
import org.eclipsescout.demo.widgets.client.ui.forms.MessageBoxForm.MainBox.ConfigurationBox.ReturnValueField;
import org.eclipsescout.demo.widgets.client.ui.forms.MessageBoxForm.MainBox.ConfigurationBox.YesButtonTextField;
import org.eclipsescout.demo.widgets.client.ui.forms.MessageBoxForm.MainBox.ExamplesBox;
import org.eclipsescout.demo.widgets.client.ui.forms.MessageBoxForm.MainBox.ExamplesBox.MessageBoxOkButton;
import org.eclipsescout.demo.widgets.client.ui.forms.MessageBoxForm.MainBox.ExamplesBox.ResultField;
import org.eclipsescout.demo.widgets.client.ui.forms.MessageBoxForm.MainBox.SampleContentButton;

public class MessageBoxForm extends AbstractForm implements IPageForm {
  private static final IScoutLogger LOG = ScoutLogManager.getLogger(MessageBoxForm.class);

  public MessageBoxForm() throws ProcessingException {
    super();
  }

  @Override
  protected boolean getConfiguredAskIfNeedSave() {
    return false;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("LabelField");
  }

  @Override
  public void startPageForm() throws ProcessingException {
    startInternal(new PageFormHandler());
  }

  public BodyField getBodyField() {
    return getFieldByClass(BodyField.class);
  }

  public HtmlField getHtmlField() {
    return getFieldByClass(HtmlField.class);
  }

  public AutoCloseMillisField getAutoCloseMillisField() {
    return getFieldByClass(AutoCloseMillisField.class);
  }

  public CancelButtonTextField getCancelButtonTextField() {
    return getFieldByClass(CancelButtonTextField.class);
  }

  @Override
  public CloseButton getCloseButton() {
    return getFieldByClass(CloseButton.class);
  }

  public DefaultReturnValueField getDefaultReturnValueField() {
    return getFieldByClass(DefaultReturnValueField.class);
  }

  public ExamplesBox getExamplesBox() {
    return getFieldByClass(ExamplesBox.class);
  }

  public HiddenTextContentField getHiddenTextContentField() {
    return getFieldByClass(HiddenTextContentField.class);
  }

  public IconIdField getIconIdField() {
    return getFieldByClass(IconIdField.class);
  }

  public HeaderField getHeaderField() {
    return getFieldByClass(HeaderField.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public MessageBoxConfiguredButton getMessageBoxConfiguredButton() {
    return getFieldByClass(MessageBoxConfiguredButton.class);
  }

  public MessageBoxOkButton getMessageBoxOkButton() {
    return getFieldByClass(MessageBoxOkButton.class);
  }

  public ConfigurationBox getConfigurationBox() {
    return getFieldByClass(ConfigurationBox.class);
  }

  public NoButtonTextField getNoButtonTextField() {
    return getFieldByClass(NoButtonTextField.class);
  }

  public ReturnValueField getReturnValueField() {
    return getFieldByClass(ReturnValueField.class);
  }

  public SampleContentButton getSampleContentButton() {
    return getFieldByClass(SampleContentButton.class);
  }

  public YesButtonTextField getYesButtonTextField() {
    return getFieldByClass(YesButtonTextField.class);
  }

  public ResultField getResultField() {
    return getFieldByClass(ResultField.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(10.0)
    public class ExamplesBox extends AbstractGroupBox {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Examples");
      }

      @Order(10.0)
      public class MessageBoxOkButton extends AbstractLinkButton {

        @Override
        protected int getConfiguredGridW() {
          return 1;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("MessageBoxWithOkButton");
        }

        @Override
        protected boolean getConfiguredProcessButton() {
          return false;
        }

        @Override
        protected void execClickAction() throws ProcessingException {
          int result = MessageBoxes.createOk().header(TEXTS.get("MessageBoxWithOkButton")).body(TEXTS.get("Lorem")).show();
          getResultField().setMessageBoxResult(result);
        }
      }

      @Order(20.0)
      public class MessageBoxYesNoButton extends AbstractLinkButton {

        @Override
        protected int getConfiguredGridW() {
          return 1;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("MessageBoxWithYesNoButton");
        }

        @Override
        protected boolean getConfiguredProcessButton() {
          return false;
        }

        @Override
        protected void execClickAction() throws ProcessingException {
          int result = MessageBoxes.createYesNo().header(TEXTS.get("Lorem")).body("Press \"Yes\" or \"No\"").show();
          getResultField().setMessageBoxResult(result);
        }
      }

      @Order(30.0)
      public class MessageBoxYesNoCancelButton extends AbstractLinkButton {

        @Override
        protected int getConfiguredGridW() {
          return 1;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("MessageBoxWithYesNoCancelButton");
        }

        @Override
        protected boolean getConfiguredProcessButton() {
          return false;
        }

        @Override
        protected void execClickAction() throws ProcessingException {
          int result = MessageBoxes.createYesNoCancel().header(TEXTS.get("Lorem")).body("Press \"Yes\", \"No\" or \"Cancel\"").show();
          getResultField().setMessageBoxResult(result);
        }
      }

      @Order(40.0)
      public class MessageBoxHiddenText extends AbstractLinkButton {

        @Override
        protected int getConfiguredGridW() {
          return 1;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("MessageBoxWithHiddenText");
        }

        @Override
        protected boolean getConfiguredProcessButton() {
          return false;
        }

        @Override
        protected void execClickAction() throws ProcessingException {
          int result = MessageBoxes.create().
              header(TEXTS.get("Lorem")).
              body(TEXTS.get("HiddenTextInstruction")).
              cancelButtonText(TEXTS.get("CloseButton")).
              hiddenText(TEXTS.get("HiddenText")).
              show();
          getResultField().setMessageBoxResult(result);
        }
      }

      @Order(50.0)
      public class DeleteConfirmationMessageButton extends AbstractLinkButton {

        @Override
        protected int getConfiguredGridW() {
          return 1;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("DeleteConfirmationMessage");
        }

        @Override
        protected boolean getConfiguredProcessButton() {
          return false;
        }

        @Override
        protected void execClickAction() throws ProcessingException {
          boolean result = MessageBoxes.showDeleteConfirmationMessage("Items", new String[]{"Item1", "Item2", "Item3"});
          getResultField().setValue(result + "");
        }
      }

      @Order(60.0)
      public class VetoExceptionButton extends AbstractLinkButton {

        @Override
        protected int getConfiguredGridW() {
          return 1;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("VetoException");
        }

        @Override
        protected boolean getConfiguredProcessButton() {
          return false;
        }

        @Override
        protected void execClickAction() throws ProcessingException {
          getResultField().setValue("(VetoException)");
          throw new VetoException("This is a VetoException");
        }
      }

      @Order(70.0)
      public class ProcessingExceptionButton extends AbstractLinkButton {

        @Override
        protected int getConfiguredGridW() {
          return 1;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("ProcessingException");
        }

        @Override
        protected boolean getConfiguredProcessButton() {
          return false;
        }

        @Override
        protected void execClickAction() throws ProcessingException {
          getResultField().setValue("(ProcessingException)");
          throw new ProcessingException("This is a ProcessingException");
        }
      }

      @Order(80.0)
      public class ResultField extends AbstractStringField {

        @Override
        protected boolean getConfiguredEnabled() {
          return false;
        }

        @Override
        protected int getConfiguredGridW() {
          return 2;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Result");
        }

        public void setMessageBoxResult(int result) {
          String optionName = "" + result;
          try {
            Field[] f = IMessageBox.class.getDeclaredFields();
            for (int i = 0; i < f.length; i++) {
              if (Modifier.isPublic(f[i].getModifiers()) && Modifier.isStatic(f[i].getModifiers()) && f[i].getName().endsWith("_OPTION")) {
                if (((Number) f[i].get(null)).intValue() == result) {
                  optionName = IMessageBox.class.getSimpleName() + "." + f[i].getName();
                  break;
                }
              }
            }
          }
          catch (Exception t) {
            LOG.warn("Error while reading fields from class", t);
          }
          setValue(optionName);
        }
      }
    }

    @Order(20.0)
    public class ConfigurationBox extends AbstractGroupBox {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Configure");
      }

      protected void updateMessageBoxConfiguredButton() {
        String yesButtonText = getYesButtonTextField().getValue();
        String noButtonText = getNoButtonTextField().getValue();
        String cancelButtonText = getCancelButtonTextField().getValue();
        long autoCloseMillis = NumberUtility.nvl(getAutoCloseMillisField().getValue(), -1);

        // Prevent opening message boxes that cannot be closed again
        boolean messageBoxClosable = (yesButtonText != null || noButtonText != null || cancelButtonText != null || autoCloseMillis != -1);
        getMessageBoxConfiguredButton().setEnabled(messageBoxClosable);
      }

      @Order(10.0)
      public class MessageBoxConfiguredButton extends AbstractLinkButton {

        @Override
        protected int getConfiguredGridW() {
          return 2;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("MessageBoxConfigured");
        }

        @Override
        protected boolean getConfiguredProcessButton() {
          return false;
        }

        @Override
        protected boolean getConfiguredEnabled() {
          return false;
        }

        @Override
        protected void execClickAction() throws ProcessingException {
          String header = getHeaderField().getValue();
          String body = getBodyField().getValue();
          IHtmlContent html = HTML.plain(getHtmlField().getValue());
          String yesButtonText = getYesButtonTextField().getValue();
          String noButtonText = getNoButtonTextField().getValue();
          String cancelButtonText = getCancelButtonTextField().getValue();
          String hiddenText = getHiddenTextContentField().getValue();
          String iconId = getIconIdField().getValue();

          long autoCloseMillis = NumberUtility.nvl(getAutoCloseMillisField().getValue(), -1);
          int defaultReturnValue = NumberUtility.nvl(getDefaultReturnValueField().getValue(), IMessageBox.CANCEL_OPTION);

          int result = MessageBoxes.create().
              header(header).
              body(body).
              html(html).
              yesButtonText(yesButtonText).
              noButtonText(noButtonText).
              cancelButtonText(cancelButtonText).
              hiddenText(hiddenText).
              iconId(iconId).
              autoCloseMillis(autoCloseMillis).
              show(defaultReturnValue);

          getReturnValueField().setValue(result);
        }
      }

      @Order(20.0)
      public class ReturnValueField extends AbstractSmartField<Integer> {

        @Override
        protected boolean getConfiguredEnabled() {
          return false;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("ReturnValue");
        }

        @Override
        protected Class<? extends ILookupCall<Integer>> getConfiguredLookupCall() {
          return (Class<? extends ILookupCall<Integer>>) AnswerOptionsLookupCall.class;
        }
      }

      @Order(40.0)
      public class HeaderField extends AbstractStringField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Header");
        }

        @Override
        protected String getConfiguredLabelFont() {
          return "ITALIC";
        }
      }

      @Order(50.0)
      public class BodyField extends AbstractStringField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Body");
        }

        @Override
        protected String getConfiguredLabelFont() {
          return "ITALIC";
        }
      }

      @Order(60.0)
      public class HtmlField extends AbstractStringField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Html");
        }

        @Override
        protected int getConfiguredGridH() {
          return 4;
        }

        @Override
        protected boolean getConfiguredMultilineText() {
          return true;
        }

        @Override
        protected boolean getConfiguredWrapText() {
          return true;
        }

        @Override
        protected String getConfiguredLabelFont() {
          return "ITALIC";
        }
      }

      @Order(70.0)
      public class YesButtonTextField extends AbstractStringField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("YesButtonText");
        }

        @Override
        protected String getConfiguredLabelFont() {
          return "ITALIC";
        }

        @Override
        protected void execChangedValue() throws ProcessingException {
          updateMessageBoxConfiguredButton();
        }
      }

      @Order(80.0)
      public class NoButtonTextField extends AbstractStringField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("NoButtonText");
        }

        @Override
        protected String getConfiguredLabelFont() {
          return "ITALIC";
        }

        @Override
        protected void execChangedValue() throws ProcessingException {
          updateMessageBoxConfiguredButton();
        }
      }

      @Order(90.0)
      public class CancelButtonTextField extends AbstractStringField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("CancelButtonText");
        }

        @Override
        protected String getConfiguredLabelFont() {
          return "ITALIC";
        }

        @Override
        protected void execChangedValue() throws ProcessingException {
          updateMessageBoxConfiguredButton();
        }
      }

      @Order(110.0)
      public class HiddenTextContentField extends AbstractStringField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("HiddenTextContent");
        }

        @Override
        protected String getConfiguredLabelFont() {
          return "ITALIC";
        }
      }

      @Order(120.0)
      public class IconIdField extends AbstractSmartField<String> {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("IconId");
        }

        @Override
        protected String getConfiguredLabelFont() {
          return "ITALIC";
        }

        @Override
        protected Class<? extends ILookupCall<String>> getConfiguredLookupCall() {
          return (Class<? extends ILookupCall<String>>) IconIdLookupCall.class;
        }
      }

      @Order(130.0)
      public class DefaultReturnValueField extends AbstractSmartField<Integer> {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("DefaultReturnValue");
        }

        @Override
        protected String getConfiguredLabelFont() {
          return "ITALIC";
        }

        @Override
        protected Class<? extends ILookupCall<Integer>> getConfiguredLookupCall() {
          return (Class<? extends ILookupCall<Integer>>) AnswerOptionsLookupCall.class;
        }
      }

      @Order(140.0)
      public class AutoCloseMillisField extends AbstractLongField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("AutoCloseMillis");
        }

        @Override
        protected String getConfiguredLabelFont() {
          return "ITALIC";
        }

        @Override
        protected Long getConfiguredMinValue() {
          return -1L;
        }

        @Override
        protected void execChangedValue() throws ProcessingException {
          updateMessageBoxConfiguredButton();
        }
      }
    }

    @Order(30.0)
    public class CloseButton extends AbstractCloseButton {
    }

    @Order(40.0)
    public class SampleContentButton extends AbstractButton {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("SampleContent");
      }

      @Override
      protected void execClickAction() throws ProcessingException {
        getHeaderField().setValue(TEXTS.get("LoremQuestion"));
        getBodyField().setValue(TEXTS.get("LoremAction"));
        getHtmlField().setValue(TEXTS.get("LoremHtml"));
        getYesButtonTextField().setValue(TEXTS.get("YesButton"));
        getNoButtonTextField().setValue(TEXTS.get("NoButton"));
        getHiddenTextContentField().setValue(TEXTS.get("Lorem"));
        getIconIdField().setValue("status_info");
        getDefaultReturnValueField().setValue(IMessageBox.NO_OPTION);
        getAutoCloseMillisField().setValue(5000L);
      }
    }
  }

  public class PageFormHandler extends AbstractFormHandler {
  }
}