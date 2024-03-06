package com.cg.railwayreservation.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.railwayreservation.admin.adminProxy.BookingProxy;
import com.cg.railwayreservation.admin.adminProxy.IssueProxy;
import com.cg.railwayreservation.admin.adminProxy.TrainProxy;
import com.cg.railwayreservation.admin.model.LoginModel;
import com.cg.railwayreservation.admin.service.ServiceImpl;
import com.cg.railwayreservation.admin.vo.BookingModel;
import com.cg.railwayreservation.admin.vo.HelpModel;
import com.cg.railwayreservation.admin.vo.TrainBookingVo;
import com.cg.railwayreservation.admin.vo.TrainModel;
import com.cg.railwayreservation.admin.vo.UserIssueVO;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;




@Slf4j
@RestController
@RequestMapping("/registration/autherization")
//@CrossOrigin(origins = "http://localhost:4200",methods = {RequestMethod.PUT,RequestMethod.GET,RequestMethod.DELETE,RequestMethod.POST})
public class AuthorizeController {
	
	@Autowired
	private ServiceImpl serviceImpl;
	
	@Autowired
	private TrainProxy proxy;
	
	@Autowired
	private BookingProxy bookingProxy;

	@Autowired
	private IssueProxy issueProxy;
	
//....................................Login-RestApi..........................................//	
	
	     //Login Crud Operations Started
	
//	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<LoginModel>> getAllUsers(){
		log.info("Inside the getAllLUsers of AuthorizeController Class");
		log.info("Retriving the List of Logins form Database");
		return ResponseEntity.ok(serviceImpl.getAllUsers());			
	}
	
	@GetMapping("/getbyUsername/{username}")
	public ResponseEntity<LoginModel> getbyUserName(@PathVariable String username){
		log.info("Inside the getbyusername method of Authorize Controlller");
		log.info("Retriving the userName");
		return ResponseEntity.ok(serviceImpl.getByUsername(username));
	}
	
	@PreAuthorize("hasAnyAuthority('User', 'Admin')")
	@PutMapping("/updatethepassword/{username}")
	public ResponseEntity<LoginModel> updatePassword(@PathVariable String username, @RequestBody LoginModel Login ){
		log.info("Success Password is Updated");
		log.info("Inside the updateBuUsername method of Authorize Controller Class");
		return ResponseEntity.ok(serviceImpl.updatePassword(username, Login));
	}
	
	@PutMapping("/updateUserDetails/{username}")
	public ResponseEntity<LoginModel> updateByUserDetails(@PathVariable String username, @RequestBody LoginModel login){
		log.info("Success UserDetails is Updated");
		log.info("Inside the updateByUserDetails method of Authorize Controller Class");
		return ResponseEntity.ok(serviceImpl.updateByUserDetails(username, login));
	}
	@DeleteMapping("/deleteUser/{username}")
	public ResponseEntity<String> deleteUser(@PathVariable String username){
		serviceImpl.DeleteUser(username);
		return ResponseEntity.ok("User Deleted Successfully");
	}
	
	                    //Login Crud Operations are Done //
	
//.....................................TRAIN-RESTAPI...................................................................//	
	
	
	                  //Train Crud Operations Started//
	
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping("/addtrain")
	public String addtrain(@RequestBody TrainModel trainmodel) {
		log.info("Train Add method Strated Inside the Authorize Controller Class");
		
		proxy.addtrain(trainmodel);
		
		log.info("Train details Successfully Added using the Login Controller");
		return "Train with No: "+trainmodel.getTrainNo()+" have been added Successfully";
	}
	
	
//	@PreAuthorize("hasAnyAuthority('User', 'Admin')")
	@GetMapping("/viewalltrains")
	public List<TrainModel> getAllTrains(){
		log.info("viewalltrains Method Started Inside the Authorize Controller");
		return proxy.getTrainModel();
	}
	
	@PreAuthorize("hasAnyAuthority('User', 'Admin')")
	@GetMapping("/viewtrainbyno/{trainNo}")
	public TrainModel getTrainByNo(@PathVariable String trainNo) {
		log.info("viewtrainbyno Method Started Inside the Authorize Controller");
		
		return proxy.getTrainByNo(trainNo);
	}
	
	@PreAuthorize("hasAnyAuthority('User', 'Admin')")
	@GetMapping("/viewtrainbyname/{trainName}")
	public List<TrainModel> getTrainsbyname(@PathVariable String trainName){
		log.info("**************************************************************");
		log.info("viewtrainbyname Method Started Inside the Authorize Controller");
		return proxy.getTrainsbyname(trainName);
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@DeleteMapping("/deletetrain/{trainNo}")
	public String deleteTrain(@PathVariable String trainNo) {
		log.info("deletetrain Method Started Inside the Authorize Controller");
		proxy.deleteTrain(trainNo);
		log.info("Train Delete Successfull. ");
		return "Train with no."+trainNo+" have been deleted";
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@PutMapping("/udatetrain/{trainNo}")
	public String updateTrain(@PathVariable String trainNo,@RequestBody TrainModel trainModel) {
		log.info("updateTrain Method Started Inside the Authorize Controller");
		proxy.updateTrain(trainNo, trainModel);
		log.info("Train with no."+trainNo+" have been updated in side the Authorize Controller");
		return "Train with no."+trainNo+" have been updated";
	}
	
//	@PreAuthorize("hasAnyAuthority('User', 'Admin')")
	@GetMapping("/findbetween/{trainFrom}/{trainTo}")
	public List<TrainModel> findByLocation(@PathVariable String trainFrom, @PathVariable String trainTo){
		log.info("findByLocation Method Started Inside the Authorize Controller");
		return proxy.findByLocation(trainFrom, trainTo);
	}
	
	

//..........................................................................................................//	

	
	
	@PreAuthorize("hasAuthority('User')")
	@PostMapping("/booking/{username}")
	public String bookTicket(@RequestBody BookingModel booking, @PathVariable String username) {
		log.info("bookTicket Method Started Inside the Authorize Controller"+booking);
		booking.setUsername(username);
		return bookingProxy.bookTicket(booking);
	
	}
	
	@GetMapping("/ViewAllBookings")
    public List<BookingModel> viewAllBookings() {
		return bookingProxy.viewAllBookings();
	}

//	@PreAuthorize("hasAuthority('User')")
	@DeleteMapping("/cancelingTicketByPnr/{pnr}")
    public String cancelTicket(@PathVariable String pnr) {
		log.info("cancelTicket Method Started Inside the Authorize Controller");
		return bookingProxy.cancelTicket(pnr);
	}
	
	@PreAuthorize("hasAnyAuthority('User', 'Admin')")
	@GetMapping("/ViewTicketByPnr/{pnr}") 
	public BookingModel viewByPnr(@PathVariable String pnr) {
		log.info("viewByPnr Method Started Inside the Authorize Controller");
		return bookingProxy.viewByPnr(pnr);
	}
	
//	 @PreAuthorize("hasAuthority('User')")
	@GetMapping("/viewByUserName/{username}")
	public List<BookingModel> viewByUserName(@PathVariable String username){
		return bookingProxy.viewByUserName(username);
	}
	
	@PreAuthorize("hasAnyAuthority('User', 'Admin')")
    @GetMapping("/ViewBookingTicketByItsTrainAndTotalCost/{pnr}")
	public TrainBookingVo getBookingTicketByItsTrainAndTotalCost(@PathVariable String pnr) {
		log.info("getBookingTicketByItsTrainAndTotalCost Method Started Inside the Authorize Controller");
    	return bookingProxy.getBookingTicketByItsTrainAndTotalCost(pnr);
    }
	
	
//*****************************ISSUE-SERVICE****************************************//
	
	
	@PreAuthorize("hasAuthority('User')")
	@PostMapping("/issue/addIssue/{username}")
    public String addissue( @RequestBody HelpModel helpModel, @PathVariable String username) {
		log.info("addissue Method Started Inside the Authorize Controller");
		helpModel.setUsername(username);
		return issueProxy.addissue(helpModel);
	}

	@PreAuthorize("hasAnyAuthority('User', 'Admin')")
	@GetMapping("/issue/getAllIssues")
	public List<HelpModel> getAllIssues(){
		log.info("getAllIssues Method Started Inside the Authorize Controller");
		return issueProxy.getAllIssues();
	}
	

	@PreAuthorize("hasAnyAuthority('User', 'Admin')")
	@GetMapping("/issue/getByUsername/{username}")
	public UserIssueVO getByUsername(@PathVariable String username) {
		log.info("getByUsername Method Started Inside the Authorize Controller");
		return issueProxy.getByUsername(username);
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@PutMapping("/issue/update/{issue}")
	public HelpModel updateIssue(@RequestBody HelpModel helpModel, @PathVariable String issue) {
		log.info("updateIssue Method Started Inside the Authorize Controller");
		return issueProxy.updateIssue(helpModel, issue);
	}
	
	@PreAuthorize("hasAnyAuthority('User', 'Admin')")
	@GetMapping("/issue/getUserissues/{username}")
	public List<HelpModel> getUserissues(@PathVariable String username){
		return issueProxy.getUserissues(username);
	}
}
