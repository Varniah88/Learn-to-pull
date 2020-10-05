package consumer_01;

import java.util.*;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import gasservice.Gas_Interface;
import reloadservice.Reload_Interface;
import travelservice.Travel_Interface;

public class Consumer_Activator implements BundleActivator {
	ServiceReference serviceReferance;

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Consumer_Activator.context = bundleContext;
		System.out.println("Consumer bundule started.");
		this.MainMethod();
		System.out.println("Consumer service started.");
	}
	
	private void MainMethod(){
		Scanner sc=new Scanner(System.in);
		String user_option="no";
		
		do {
			int serviceType = SubMethod.find();
			
			if(serviceType == 1) {
				
				ServiceReference service_1 = context.getServiceReference(Gas_Interface.class.getName());
				Gas_Interface Gas_Int =(Gas_Interface)context.getService(service_1);
				SubMethod.gasService(Gas_Int);
				context.ungetService(service_1);
			}
			else if(serviceType == 2) {
				
				ServiceReference service_2 = context.getServiceReference(Reload_Interface.class.getName());
				Reload_Interface Reload_Int =(Reload_Interface)context.getService(service_2);
				SubMethod.reloadService(Reload_Int);
				context.ungetService(service_2);
			}
			else if(serviceType == 3) {
				
				ServiceReference service_3 = context.getServiceReference(Travel_Interface.class.getName());
				Travel_Interface Travel_Int =(Travel_Interface)context.getService(service_3);
				SubMethod.travelService(Travel_Int);
				context.ungetService(service_3);
			}
			else {
				System.out.println("");
			}
			System.out.println("Do you want to exit? \n 'yes' for YES \n 'no' for NO");
			user_option = sc.next();
			
		}while(user_option.equals("no"));	
		SubMethod.PrintBill();
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Consumer_Activator.context = null;
		System.out.println("Consumer service stopped.");
	}

}
