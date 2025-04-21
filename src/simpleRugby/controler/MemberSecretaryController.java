package simpleRugby.controler;

import simpleRugby.view.MemberSecretaryGUI;
import simpleRugby.controler.RegisterController;


/**
 * The MemberSecretaryController sets up and manages the Member Secretary's part of the system.
 * 
 * It creates the GUI for the Member Secretary, sets it visible, and also links in the 
 * RegisterController so the secretary can register new members or manage user details.
 * 
 * This controller acts as point of control for the Member Secretary's interface.
 */

public class MemberSecretaryController {

    private MemberSecretaryGUI memberSecretaryGUI; // main view for Member Secretary



    public MemberSecretaryController(MemberSecretaryGUI memberSecretaryGUI) {

    	// new RegisterController so the secretary can use registration features
        RegisterController registerController = new RegisterController();
        
        // create gui and pass both controllers to it
        memberSecretaryGUI = new MemberSecretaryGUI(this, registerController);
        
        // shows GUI to the user
        memberSecretaryGUI.setVisible(true);
	}

	public MemberSecretaryController() {
		// TODO Auto-generated constructor stub
	}

	// setter method to access the GUI if needed elsewhere
    public MemberSecretaryGUI getMemberSecretaryGUI() {
        return memberSecretaryGUI;
    }
}
