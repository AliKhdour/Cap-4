package com.techelevator.controller;

import com.techelevator.DAO.JDBCHomeDAO;
import com.techelevator.DAO.JDBCUserTaskDAO;
import com.techelevator.authentication.AuthProvider;
import com.techelevator.authentication.UnauthorizedException;
import com.techelevator.authentication.UserCreationException;
import com.techelevator.interfaces.ApplianceDAO;
import com.techelevator.interfaces.ApplianceTypeDAO;
import com.techelevator.interfaces.HomeDAO;
import com.techelevator.interfaces.InfrastructureTypeDAO;
import com.techelevator.interfaces.MaintenanceTaskDAO;
import com.techelevator.interfaces.MechanicalAssetDAO;
import com.techelevator.interfaces.MechanicalAssetTypeDAO;
import com.techelevator.interfaces.ProfileDAO;
import com.techelevator.interfaces.TaskDAO;
import com.techelevator.interfaces.UserTaskDAO;
import com.techelevator.model.Appliance;
import com.techelevator.model.ApplianceType;
import com.techelevator.model.Home;
import com.techelevator.model.InfrastructureType;
import com.techelevator.model.JdbcUserDao;
import com.techelevator.model.MaintenanceTask;
import com.techelevator.model.MechanicalAsset;
import com.techelevator.model.MechanicalAssetType;
import com.techelevator.model.Profile;
import com.techelevator.model.Task;
import com.techelevator.model.User;
import com.techelevator.model.UserDao;
import com.techelevator.model.UserTask;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * ApiController
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiController {
	
	@Autowired
    private TaskDAO taskDAO;
	@Autowired
    private UserTaskDAO userTaskDAO;
    @Autowired
    private AuthProvider authProvider;
    @Autowired 
    private HomeDAO homeDao;
    @Autowired
    private ApplianceDAO applianceDao;
    @Autowired
    private MechanicalAssetDAO assetDao;
    @Autowired
    private MaintenanceTaskDAO taskDao;
    @Autowired
    private ProfileDAO profileDao;
    @Autowired
    private ApplianceTypeDAO applianceTypeDao;
    @Autowired
    private MechanicalAssetTypeDAO mechanicalAssetTypeDao;
    @Autowired
    private InfrastructureTypeDAO infrastructureTypeDao;
    @Autowired
    private JdbcUserDao userDao;
    

    @Autowired
    public ApiController(AuthProvider authProvider ) {
    	this.authProvider = authProvider;
    }
    
    @GetMapping("/appliances/{id}")
    public List<Appliance> applianceList(@PathVariable int homeId) {  //might not be @RequestParam
        List<Appliance> appliances = applianceDao.getAllAppliances(homeId);
        return appliances; 
    }
    
    @GetMapping("/mechanicalassets")
    public List<MechanicalAsset> assetList(@RequestParam int homeId) {  //might not be @RequestParam
        List<MechanicalAsset> assets = assetDao.getAllMechanicalAssets(homeId);
        return assets; 
    }
    
    @GetMapping("/maintenancetasks")
    public List<MaintenanceTask> taskList(@RequestParam int homeId) {  //might not be @RequestParam
        List<MaintenanceTask> tasks = taskDao.getAllMaintenanceTasksByHome(homeId);
        return tasks; 
    }
    
    @GetMapping("/profile")
    public Profile getProfile(@RequestParam String username) throws UserNotFoundException {
    	String loggedInUser = authProvider.getCurrentUser().getFirstName();
    	
    	System.out.println("Logged in user is " + loggedInUser);
    	System.out.println("User name passed in is: " + username);
    	
    	Profile aProfile = profileDao.getProfileForUser(username);
    	System.out.println(aProfile);
    
    		return aProfile;
    	
    }
    @GetMapping("/homes")
    public List<Home> getHomes(@RequestParam String username) throws UserNotFoundException {
    	List<Home> listOfHomes;
    	String loggedInUser = authProvider.getCurrentUser().getFirstName();
    	Profile aProfile = profileDao.getProfileForUser(username);
    	
    	listOfHomes = homeDao.getHomesByOwner(aProfile.getId());
    	System.out.println(listOfHomes);
    	return listOfHomes;
    }
    
    @GetMapping("/appliances")
    public List<Appliance> getAppliances(@RequestParam int homeId) throws UserNotFoundException {
    	List<Appliance> listOfAppliances;
    	listOfAppliances = applianceDao.getAllAppliances(homeId);
    	
    	return listOfAppliances;
    }
    
    @GetMapping("/appliancetype")
    public List<ApplianceType> applianceList() {
    	List<ApplianceType> appliances = applianceTypeDao.getAllApplianceTypes();
    	return appliances;
    }
    
    @GetMapping("/mechanicalassettype")
    public List<MechanicalAssetType> mechanicalAssetList() {
    	List<MechanicalAssetType> mechAssets = mechanicalAssetTypeDao.getAllMechanicalAssetTypes();
    	return mechAssets;
    }

    @GetMapping("/infrastructuretype")
    public List<InfrastructureType> infrastructureList() {
    	List<InfrastructureType> infrastructureAssets = infrastructureTypeDao.getAllInfrastructureTypes();
    	return infrastructureAssets;
    }
    
    @PostMapping(path = "/newappliance", consumes = "application/json", produces = "application/json")
    public void addAppliance(@RequestBody Appliance newAppliance) {
    	List<Task> allApplianceTasks = new ArrayList<Task>();
        Appliance anAppliance = new Appliance();
        int applianceId = 0;
        
        anAppliance.setAge(newAppliance.getAge());
        anAppliance.setHomeId(newAppliance.getHomeId());
        anAppliance.setType(newAppliance.getType());
        anAppliance.setModelNumber(newAppliance.getModelNumber());
        anAppliance.setApplianceId(newAppliance.getApplianceId());
        
        //Add appliance, then get the new (serialized) ID to be used when setting up the tasks for the appliance
        applianceId = applianceDao.addAppliance(anAppliance);
        anAppliance.setApplianceId(applianceId);
        
        allApplianceTasks = taskDAO.getTasksByType(anAppliance.getType());
        
        //Create a task in user_task table for each associated task from the task table
        for(Task aTask : allApplianceTasks) {
        	userTaskDAO.newUserApplianceTask(aTask.getTaskId(), anAppliance.getApplianceId(), anAppliance.getHomeId());
        }
        
        
        System.out.println(anAppliance);
        
        
    }
    
    @PostMapping(path = "/newhome", consumes = "application/json", produces = "application/json")
    public void addHome(@RequestBody Home newHome) {
        
    	homeDao.addHome(newHome);
        
    }
    
    @PutMapping(path = "/usertask",consumes = "application/json", produces = "application/json")
    public UserTask resetTask(int taskId) {
    	userTaskDAO.updateLastPerformed(userTaskDAO.getTaskByUserTaskId(taskId).getUserTaskId());
    	return userTaskDAO.getTaskByUserTaskId(taskId);
    }
    
//    @GetMapping("/appliancetype")
//    public List<ApplianceType> applianceList() {
//    	List<ApplianceType> appliances = applianceTypeDao.getAllApplianceTypes();
//    	return appliances;
//    }
    
}
    
    
