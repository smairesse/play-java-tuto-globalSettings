import play.*;

import play.mvc.*;
import play.mvc.Http.*;
import play.libs.F.*;

import static play.mvc.Results.*;

import java.lang.reflect.Method;

import java.io.File;
import java.lang.ClassLoader;

/**
 * @author Stéphane Mairesse
 * @see https://www.playframework.com/documentation/2.x.x/JavaGlobal
 * @see https://www.playframework.com/documentation/2.x.x/api/java/play/GlobalSettings.html
 */
public class Global extends GlobalSettings {

	// Intercepting application start-up and shutdown

	// onLoadConfig
	/** 
	 * Called just after configuration has been loaded, to give the application an opportunity to modify it.
	 */
	public Configuration onLoadConfig(Configuration config, File path, ClassLoader classloader) {
	   Logger.info("onLoadConfig - Configuration loaded... " + config.toString());
	   return super.onLoadConfig(config, path, classloader);
	}

	// beforeStart
	/**
	 * Executed before any plugin - you can set-up your database schema here, for instance.
	 */
	public void beforeStart(Application app) {
		Logger.info("onBeforeStart - Application on start");
	}  

	// onStart
	/** 
	 * Executed after all plugins, including the database set-up with Evolutions and the EBean wrapper. This is a good place to execute some of your application code to create entries, for instance.
	 */
	public void onStart(Application app) {
		Logger.info("onStart - Application has started");
	}  

	// onRequest
	/**
	 * Call to create the root Action of a request for a Java application
	 */
	public Action onRequest(Request request, Method actionMethod) {
	   Logger.info("onRequest - Before request... " + request.toString());
	   return super.onRequest(request, actionMethod);
	}

	// onStop
	/**
	 * Executed when the application stops.
	 */
	public void onStop(Application app) {
		Logger.info("onStop - Application shutdown...");
	}  

	// Providing an application error page
  
	// onError
	/**
	 * Returns a Result that could be a custom error page
	 */
	public Promise<SimpleResult> onError(RequestHeader request, Throwable t) {
		Logger.info("onError - Error : " + t.toString());
		return Promise.<SimpleResult>pure(internalServerError(
			views.html.errors.errorPage.render(request.method(), request.uri(), request.path(), t.getLocalizedMessage(), t.getMessage())
		));
    }

	// onHandlerNotFound
	/**
	 * Triggered when a resource was requested but not found
     */
	public Promise<SimpleResult> onHandlerNotFound(RequestHeader request) {
	   Logger.info("onHandlerNotFound - Not found : " + request.toString());
		return Promise.<SimpleResult>pure(notFound(
			views.html.errors.notFoundPage.render(request.method(), request.uri(), request.path())
		));
    }

	// onBadRequest
	/**
	 * Triggered when a resource was requested but not found, the default implementation returns null, so that the Scala engine handles the onBadRequest.
	 */
	public Promise<SimpleResult> onBadRequest(RequestHeader request, String error) {
		Logger.info("onBadRequest - Bad request : " + request.toString());
		return Promise.<SimpleResult>pure(notFound(
			views.html.errors.badRequestPage.render(request.method(), request.uri(), request.path(), error)
		));
    }

}
