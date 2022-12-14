package seproject;

import javafx.event.EventTarget;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class EventGenerator {
        /**
         * Utility method that generate in easy way a mouse left click event
         * @param source the object that is the source of the event
         * @param destination the object on which the mouse clicked
         * @param x coordinate of the click
         * @param y coordinate of the click
         * @return a new mouse event correctly generated.
         */
        public static MouseEvent PrimaryButtonMousePressed(Object source, EventTarget destination, double x , double y){
            return new MouseEvent(source,destination,MouseEvent.MOUSE_PRESSED,x,y,0,0,MouseButton.PRIMARY,1,false,false,false,false,true,false,false,false,false,false,null);
        }
        
        /**
         * Utility method that generate in easy way a mouse left drag event
         * @param source the object that is the source of the event
         * @param destination the object on which the mouse clicked
         * @param x coordinate of the click
         * @param y coordinate of the click
         * @return a new mouse event correctly generated.
         */
        public static MouseEvent PrimaryButtonMouseDragged(Object source, EventTarget destination, double x , double y){
            return new MouseEvent(source,destination,MouseEvent.MOUSE_DRAGGED,x,y,0,0,MouseButton.PRIMARY,1,false,false,false,false,true,false,false,false,false,false,null);
        };
        
        /**
         * Utility method that generate in easy way a mouse left click event
         * @param source the object that is the source of the event
         * @param destination the object on which the mouse clicked
         * @param x coordinate of the click
         * @param y coordinate of the click
         * @return a new mouse event correctly generated.
         */
        public static MouseEvent PrimaryButtonMouseReleased(Object source, EventTarget destination, double x , double y){
            return new MouseEvent(source,destination,MouseEvent.MOUSE_RELEASED,x,y,0,0,MouseButton.PRIMARY,1,false,false,false,false,true,false,false,false,false,false,null);
        }
        
}
