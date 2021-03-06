package org.sdmlib.test.examples.groupaccount.gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Locale;

import org.sdmlib.test.examples.groupaccount.model.Item;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ItemController implements PropertyChangeListener
{

   private PersonController personController;
   private Item item;
   
   private HBox view = null;
   
   public HBox getView()
   {
      return view;
   }
   
   private VBox itemList;
   
   public VBox getItemList()
   {
      return itemList;
   }
   
   private Button addItemButton;

   public ItemController(PersonController personController, Item item, VBox itemList, Button addItemButton)
   {
      this.personController = personController;
      this.item = item;
      this.itemList = itemList;
      this.addItemButton = addItemButton;
      
      item.getPropertyChangeSupport().addPropertyChangeListener(this);
      item.getPropertyChangeSupport().firePropertyChange(null, null, null);
   }

   private JavafxUtils javafxUtils = new JavafxUtils();
   
   private TextField textField;
   
   public TextField getTextField()
   {
      return textField;
   }
   
   private TextField valueField;
   
   public TextField getValueField()
   {
      return valueField;
   }
   
   
   @Override
   public void propertyChange(PropertyChangeEvent evt)
   {
      if (view == null)
      {
         view = new HBox(8);
         view.setPadding(new Insets(0,4,0,0));
         itemList.getChildren().add(view);
         
         textField = javafxUtils.createTextField(view);
         textField.setPrefWidth(200);
         
         PropertyChangeListener listener = new PropertyChangeListener()
         {
            @Override
            public void propertyChange(PropertyChangeEvent evt)
            {
               textField.setText(item.getDescription());
            }
         };
         
         item.getPropertyChangeSupport().addPropertyChangeListener(Item.PROPERTY_DESCRIPTION, listener);
         
         listener.propertyChange(null);
         
         textField.textProperty().addListener(new ChangeListener<String>()
            {
               @Override
               public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2)
               {
                  item.setDescription(textField.getText());
               }
            });
         
         
         // javafxUtils.bindString(textField, item, Item.PROPERTY_DESCRIPTION);
         textField.requestFocus();
         textField.selectAll();
         
         
         valueField = javafxUtils.createTextField(view);
         valueField.setAlignment(Pos.CENTER_RIGHT);
         valueField.setPrefWidth(60);
         bindItemValue(valueField);
         
         Button button = new Button("Del");
         view.getChildren().add(button);
         button.setVisible(false);
         javafxUtils.addVisibilityToggle(view, button, addItemButton);
         
         button.setOnAction(new EventHandler<ActionEvent>()
         {
            @Override
            public void handle(ActionEvent event)
            {
               if (item.getBuyer().getItem().size() <= 1) return;
               
               // remove from model
               item.removeYou();
               // remove from view
               itemList.getChildren().remove(view);
            }
         });
      }
   }

   private void bindItemValue(final TextField textField)
   {
      PropertyChangeListener listener = new PropertyChangeListener()
      {
         @Override
         public void propertyChange(PropertyChangeEvent evt)
         {
            String text = String.format(Locale.ENGLISH, "%.2f €", item.getValue());
            textField.setText(text);
         }
      };
      item.getPropertyChangeSupport().addPropertyChangeListener(Item.PROPERTY_VALUE, listener);
         
      listener.propertyChange(null);
      
      textField.focusedProperty().addListener(new ChangeListener<Boolean>()
      {

         @Override
         public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
         {
            parseInput(textField);
               
            // show add item button
            addItemButton.setVisible(newValue);
         }

      });
      
      
      textField.setOnAction(new EventHandler<ActionEvent>()
         {
         @Override
         public void handle(ActionEvent event)
         {
            parseInput(textField);
         }
         });
      
   }

   private void parseInput(TextField textField)
   {
      String source = textField.getText();

      int europos = source.indexOf("€");
       
      if (europos >= 0)
      {
         source = source.substring(0, europos);
      }
      
      try {
         double value = Double.parseDouble(source);
         item.setValue(value);
      } catch(Exception e) {}
   }
}
