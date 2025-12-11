/*******************************************************************
 * Name: Holly Hebert
 * Date: December 10, 2025
 * Assignment: SDC330 Week 5 – Final Project
 * Class: Manageable (Interface)
 *
 * Description:
 * Defines standard method signatures for CRUD operations
 * (Create, Read, Update, Delete). Implemented by manager
 * classes like PetManager, AppointmentManager, etc.
 *******************************************************************/

public interface Manageable {
    void add();
    void update();
    void delete();
    void view();
}