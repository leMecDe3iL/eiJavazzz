package jfox.javafx.view;

import javafx.stage.Stage;


public interface IManagerGui {

	Stage getStage();

	void showView(Class<?> type);

	void showView( IEnumView idView );

	void showDialog(Class<?> typeView);

	void showDialog(Class<?> typeView, Class<? extends IConfigDialog> typeConfigDialog);

	void showDialog( IEnumView idView );

	void showDialog( IEnumView idView, Class<? extends IConfigDialog> typeConfigStage );

	void closeDialog();

	void exit();

	void execTask( RunnableWithException runnable );

	void showDialogMessage( String message );

	boolean showDialogConfirm( String message );

	void showDialogError( Throwable exception );

	void showDialogError( String message );
	
	void showDocument( String uri );
	
	
	// Type auxiliaire
	
	public static interface RunnableWithException {
		void run() throws Exception;
	}

}