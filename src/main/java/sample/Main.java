package sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * Hello World
 */
// 继承javafx.application.Application是JavaFX的开始
public class Main extends Application {

  /**
   * Stage：就是你能看到的整个软件界面（窗口）
   * Scene：就是除了窗口最上面有最大、最小化及关闭按钮那一行及窗口边框外其它的区域（场景）
   * 场景（Scene）是一个窗口（Stage）必不可少的
   */
  @Override
  public void start(Stage stage) throws Exception {
    // （如果需要的话）定位横纵坐标,避免太靠边上遮盖菜单栏,，这两行如果不屑，一般电脑默认是居中屏幕显示，但在有些电脑会跑偏
    // stage和Scene不再注释
    stage.setTitle(Constants.TITLE);
    //stage.getIcons().add(new Image(Constants.IMG + "icon.png"));
    stage.setWidth(1400);
    // 窗口的最小宽度
    stage.setMinWidth(800);
    // 窗口的高度
    stage.setHeight(1000);
    // 窗口的最小高度
    stage.setMinHeight(600);
    // 设置宽高尺寸可调整，true:可以拖拽边缘调整窗口尺寸，false：不可调整
    stage.setResizable(true);



    //使用tab切换界面，tab1是子豪，tab2是赵阳，tab3是罗晓彤
    TabPane tabPane=new TabPane();
    tabPane.getStyleClass().add("Menu");
    Tab tab1=new Tab("图像转换加密");
    tab1.getStyleClass().add("MenuItem");
    Tab tab2=new Tab("图像隐写加密");
    tab2.getStyleClass().add("MenuItem");
    Tab tab3=new Tab("视频加密");
    tab3.getStyleClass().add("MenuItem");
    tabPane.getTabs().addAll(tab1,tab2,tab3);
    //给tab2传内容
    ImageTextEncrypt ite=new ImageTextEncrypt();
    tab2.setContent(ite.ImagePane());

    // 1、初始化一个场景
    Scene scene = new Scene(tabPane, 1400, 1000);
    scene.getStylesheets().add(getClass().getClassLoader().getResource("css/Bar.css").toExternalForm());
    // 2、将场景放入窗口
    stage.setScene(scene);
    // 3、打开窗口
    stage.show();
//下面可以实现拖拽图片进入图片框
    ite.getImg().setOnDragEntered(new EventHandler<DragEvent>() {
      @Override
      public void handle(DragEvent event) {
        ite.getImg().setBorder(new Border(new BorderStroke(Paint.valueOf("#00ffff"),
            BorderStrokeStyle.SOLID,new CornerRadii(0),new BorderWidths(1))));
      }
    });
    ite.getImg().setOnDragExited(new EventHandler<DragEvent>() {
      @Override
      public void handle(DragEvent event) {
        ite.getImg().setBorder(null);
      }
    });
    ite.getImg().setOnDragOver(new EventHandler<DragEvent>() {
      @Override
      public void handle(DragEvent event) {
        event.acceptTransferModes(event.getTransferMode());
      }
    });
    ite.getImg().setOnDragDropped(new EventHandler<DragEvent>() {
      @Override
      public void handle(DragEvent event) {
        Dragboard db = event.getDragboard();
        List<File> files= db.getFiles();
        if(files.size()>0){
          try{
            ite.getIv().setImage(new Image(new FileInputStream(files.get(0))));
          }catch (FileNotFoundException e){
            e.printStackTrace();
          }
        }
      }
    });
  }

  public static void main( String[] args ){
    // 启动软件
    Application.launch(args);
  }

}