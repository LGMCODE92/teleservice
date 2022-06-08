<div align="center" >
  
<img src="https://user-images.githubusercontent.com/104386449/172345214-db67921e-3697-4128-9801-25deb6e53e76.png" width="" height="75"/>
  
</div>

# Televillaverde

<p>Aportamos soluciones para gestionar el control de usuarios de ContactCenter de ayuda a personas en situación de dependencia. Desarrollamos sistemas adaptables a las necesidades de cada empresa. </p>

<p>Televillaverde es una solución para mejorar el sistema educativo del IES Villaverde, con un gran deficit de herramientas y material de clase que ayuden a los docentes a transmitir los conocimientos necesarios. </p>

## Environment
- APP
  - Eclipse Version
  - SDKVersion
  
## Prerequisites

## Installation

## Activity or Screen summary 

## Colaborations
![image](https://user-images.githubusercontent.com/104386449/172377518-4858bd8f-b846-4d9c-ab31-8994f2f34788.png)
<p><img src="https://user-images.githubusercontent.com/104386449/172345214-db67921e-3697-4128-9801-25deb6e53e76.png" width="" height="100"/></p>












# Kuikai on Android

## Introduction

[YOLO](https://pjreddie.com/darknet/yolo/) (You Only Look Once) is one of the fastest and most popular object detection models. [YOLOv5](https://github.com/ultralytics/yolov5) is an open-source implementation of the latest version of YOLO (for a quick test of loading YOLOv5 from PyTorch hub for inference, see [here](https://pytorch.org/hub/ultralytics_yolov5/#load-from-pytorch-hub)). This Object Detection with YOLOv5 Android sample app uses the PyTorch scripted YOLOv5 model to detect objects of the [80 classes](https://github.com/ultralytics/yolov5/blob/master/data/coco.yaml) trained with the model.

**Update 09-30-2021**: A new section of using a custom dataset to fine-tune the YOLOv5 model (aka transfer learning) and steps to change the Android project to use the custom model was added.

## Prerequisites

* PyTorch 1.10.0 and torchvision 0.11.1 (Optional)
* Python 3.8 (Optional)
* Android Pytorch library pytorch_android_lite:1.10.0, pytorch_android_torchvision_lite:1.10.0
* Android Studio 4.0.1 or later

## Environment
- Host Ubuntu 20.04

- Neuronal Networks
  - Pytorch
  - Yolov5s

- Android App
  - Android Studio Chipmunk | 2021.2.1
  - SdkVersion 30

- Android Device
  - Nexus 5X API 30
  - Pixel 4 API 30
  - Samsung Galaxy A71
  - Sony H3113


## Quick Start

To Test Run the Object Detection Android App, follow the steps below:

### 1. Prepare the model

If you don't have the PyTorch environment set up to run the script, you can download the model file `yolov5s.torchscript.ptl` [here](https://pytorch-mobile-demo-apps.s3.us-east-2.amazonaws.com/yolov5s.torchscript.ptl) to the `android-demo-app/ObjectDetection/app/src/main/assets` folder, then skip the rest of this step and go to step 2 directly.

The Python script `export.py` in the `models` folder of the [YOLOv5 repo](https://github.com/ultralytics/yolov5) is used to generate a TorchScript-formatted YOLOv5 model named `yolov5s.torchscript.pt` for mobile apps.

Open a Mac/Linux/Windows Terminal, run the following commands (note that we use the fork of the original YOLOv5 repo to make sure the code changes work, but feel free to use the original repo):

```
git clone https://github.com/ultralytics/yolov5
cd yolov5
pip install -r requirements.txt wanb
```

Note the steps below have been tested with the commit `cd35a009ba964331abccd30f6fa0614224105d39` and if there's any issue with running the script or using the model, try `git reset --hard cd35a009ba964331abccd30f6fa0614224105d39`.

Edit `export.py` to make the following two changes:

* After `f = file.with_suffix('.torchscript.pt')`, add a line `fl = file.with_suffix('.torchscript.ptl')`

* After `(optimize_for_mobile(ts) if optimize else ts).save(f)`, add `(optimize_for_mobile(ts) if optimize else ts)._save_for_lite_interpreter(str(fl))`

Now run the script below to generate the optimized TorchScript lite model `yolov5s.torchscript.ptl` and copy it to the `android-demo-app/ObjectDetection/app/src/main/assets` folder (the original full JIT model `yolov5s.torchscript.pt` was also generated for comparison):

```
python export.py --weights yolov5s.pt --include torchscript
```

Note that small sized version of the YOLOv5 model, which runs faster but with less accuracy, is generated by default when running the `export.py`. You can also change the value of the `weights` parameter in the `export.py` to generate the medium, large, and extra large version of the model.

### 2. Build with Android Studio

Start Android Studio, then open the project located in `android-demo-app/ObjectDetection`. Note the app's `build.gradle` file has the following lines:

```
implementation 'org.pytorch:pytorch_android_lite:1.10.0'
implementation 'org.pytorch:pytorch_android_torchvision_lite:1.10.0'
```

### 3. Run the app

Select an Android emulator or device to run the app. You can go through the included example test images to see the detection results. You can also select a picture from your Android device's Photos library, take a picture with the device camera, or even use live camera to do object detection - see this [video](https://drive.google.com/file/d/1-5AoRONUqZPZByM-fy0m7r8Ct11OnlIT/view) for a screencast of the app running.

Some example images and the detection results are as follows:

<div align="center">
 <img src="screenshot1.jpg" width="350" height="700"> 
 <img src="screenshot2.jpg" width="350" height="700">
</div>

<div align="center">
<img src="screenshot3.jpg" width="350" height="700">
<img src="screenshot4.jpg" width="350" height="700">
</div>	

## Transfer Learning

In this section, you'll see how to use an example dataset called [aicook](https://universe.roboflow.com/karel-cornelis-q2qqg/aicook-lcv4d/4), used to detect ingredients in your fridge, to fine-tune the YOLOv5 model. For more info on the YOLOv5 transfer learning, see [here](https://github.com/ultralytics/yolov5/issues/1314). If you use the default YOLOv5 model to do object detection on what's inside your fridge, you'll likely not get good results. That's why you need to have a custom model trained with a dataset like aicook.

### 1. Download the custom dataset

Simply go to [here](https://universe.roboflow.com/karel-cornelis-q2qqg/aicook-lcv4d/4) to download the aicook dataset in a zip file. Unzip the file to your `yolov5` repo directory, then run `cd yolov5; mv train ..; mv valid ..;` as the aicook `data.yaml` specifies the `train` and `val` folders to be up one level.

### 2. Retrain the YOLOv5 with the custom dataset

Run the script below to generate a custom model `best.torchscript.pt` located in `runs/train/exp/weights`:

```
python train.py --img 640 --batch 16 --epochs 3 --data  data.yaml  --weights yolov5s.pt
```

The precision of the model with the epochs set as 3 is very low - less than 0.01 actually; with a tool such as [Weights and Biases](https://wandb.ai), which can be set up in a few minutes and has been integrated with YOLOv5, you can find that with `--epochs` set as 80, the precision gets to be 0.95. But on a CPU machine, you can quickly train a custom model using the command above, then test it in the Android demo app. Below is a sample wandb metrics from 3, 30, and 100 epochs of training:

![](metrics.png)

### 3. Convert the custom model to lite version

With the `export.py` modified in step 1 `Prepare the model` of the section `Quick Start`, you can convert the new custom model to its TorchScript lite version:

```
python export.py --weights runs/train/exp/weights/best.pt --include torchscript
```

The resulting `best.torchscript.ptl` is located in `runs/train/exp/weights`, which needs to be copied to the Android demo app's assets folder.

## Activity summary 

In this block, the different activities will be summarised and each activities will be explained. It will also show the places where modifications would be necessary in case of adding new things to the application.

### AbstractCameraXActivity 

This activity mainly includes the Camera X settings inside in a method called `private void setupCameraX()`. Within this, the settings and implementation of the Preview that is displayed on the Live screen are established. On the other hand, the analysis settings are also set on the image that will be used by the neural network.

On the design side, three SeekBars have been created that will fulfil different functions within the Live screen.

<p align="center"> <img src="https://user-images.githubusercontent.com/102307723/171623569-9fe9b2ee-970e-453e-b935-1b9f28c14330.png" width="550" height="200"> </p>

- SeekBar: This seekbar modifies the number of detected objects according to the confidence threshold that the user wants to see with the neural network.
- SeekBar IOU: This seekbar changes the measurement of effectiveness when objects are detected
- SeekBar NumObj: As it's name indicates, this seekbar limits in the number of object the neural network can detect.

### BaseModuleActivity
	
Manages the threads so that the ProgressBar appears while the program waits for the AI's response. The `run()` method developed in both the main and the activity is initialised.
	
### Login Activity 

This activity is basically composed of two fields in which the user inserts the e-mail address and the password if the user is registered. Otherwise, he/she would have to register by clicking on the sign up button, which would take him/her to the registration screen.

<p align="center"> <img src="https://user-images.githubusercontent.com/102307723/171657765-b048714f-8ccd-4d4e-aef8-08f682a0aef9.png" width="350" height="750"> </p>

**NOTE:** The registration screen is not yet created, so if the user presses the register button, it will do nothing.

### MainActivity

The Main Activity is where most of the application's functions take place. By default, the neural network is set up that detects PIDs both from a pre-loaded image in the app, as well as when taking a photo with the device's camera or after choosing it from the gallery. Another feature of the app is that you can detect objects in real time. Of course, it has other neural networks integrated such as object detection, in which this neural network has the ability to detect up to eighty objects.

<p align="center"> <img src="https://user-images.githubusercontent.com/102307723/171851461-00f69366-1ca4-421c-8316-355ae53cc665.png" width="350" height="750"> </p>

In terms of design, this activity consists of an ImageView, which will display the preloaded images, adapted so that the user can zoom in on the photos, and a ResultView where the AI detection results will be superimposed.

In order to display the images received through Intents through the MediaStore class constant:

`Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);`
`Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);`

#### BottomNavigationView

- **Test Image**
  Performs the image change in the ImageView.

- **Detect Button**

On the other hand we have the progressBar that will become visible when the `run()` method is executed and `StartbackgroundThread()` when the user presses the **Detect** button.  

- **Choose Button**

<p align="center"> <img src="https://user-images.githubusercontent.com/102307723/171856102-f415a64c-17f3-46c9-9906-a1389e29ccfb.png" width="350" height="750"> </p>

  - **Choose from Photos:** 
  	This button takes the user to their Gallery application to choose any photo in order to test the neural network.
  - **Take Photo:** 
	The photos are transformed to bitMap before being set. Here lies the problem with the loss of quality when displaying the image. It seems that it would be necessary to store it so that it has space in the memory and is not stored with the minimum weight. 
	Once the photo is taken, the user has the option to choose whether to save the photo to the internal memory of the device or to the SD card if implemented.
  - **Cancel**
  	Cancels the action of saving the photo in the Gallery application.
	
- **Live Button:**
The Live button sends you to the ObjectDetection activity which descends from AbstractCameraX above. In both activities the camera and its respective configurations are developed.

#### NavigationView

It is a menu that includes the profile buttons, the available neural networks and VisionAnalytics' social networks, such as its website, LinkedIn and email.

<p align="center"> <img src="https://user-images.githubusercontent.com/102307723/171857410-4d2c56d0-d294-4bb9-9e0d-27fe6c763f91.png" width="350" height="750"> </p>

### ObjectsDetectionActivity.

Transforms the received AI tensor image and transforms it to Bitmap format, in this class should be the Live resolution enhancement but so far we have not been able to find it.
	
### PrePostProcessor

The AI returns many results that have to be analysed and filtered.
In the PrePostProcessor.java class, on the one hand, the Result class is defined, which will store the data of each rectangle returned by the AI in order to create instances of this object.

On the other hand, there is the PrePostProcessor class itself that stores the variables that control the data returned by the AI, these are predefined by Yolo. There are also the variables that we modify with the Seekbar.

Further on, the `nonMaxSuppression()` method is in charge of filtering the frames returned by the AI, it analyses them and depending on the value of the seekBars it filters to show the resulting frames. The `IOU()` method is a tool used in the previous method to compare two frames (also with the `Compare()` method) and obtain an average and return the most accurate one about the position of the detected object.

Finally, the `outputsToNMSPredictions()` method is responsible for finding out the name of the detected object and returns the invocation of the `nonMaxSuppression()` method that constructs each Result that will then be parsed to show only the one that meets the SeekBar parameters.

### ResultView

Result view is a widget that displays the filtered AI result and overlays it on top of the image displayed in the ZoomableImageView.

### SplashActivity

<p align="center"> <img src="https://user-images.githubusercontent.com/102307723/171859137-cc4813d8-06c6-41c7-b247-a62b320d5897.png" width="350" height="750"> </p>

SplashActivity is a simple loading screen that displays a gradient with the company's colour and logo, lasting only a few seconds before displaying the MainActivity.

### ZoomableImageView

This class is in charge of modifying the ImageView of the ActivityMain.xml so that it can zoom the image.
It uses a Matrix to store the image and be able to scale and move it. On the other hand, in the `onScaleEnd()` method, it returns it to its original size with the `ScaleType.FIT_CENTER` attribute. 

## Adding and deleting neural network.

- First of all, a new item must be created in the res/menu/menu location.

![image](https://user-images.githubusercontent.com/102307723/171860744-9d6ccfd3-a386-4ca1-9a19-0ac0b3479a5e.png)

-  Then add the file to the ASSETS directory.

![image](https://user-images.githubusercontent.com/102307675/171356500-2124077e-a949-47fc-acb8-c6f5f247e380.png)

- The file name should then be included in the appropriate menu item or a new one created (MainActivity: Line 237).

![image](https://user-images.githubusercontent.com/102307675/171391768-4f715b2d-42d4-42af-88c1-cd752f5d4df6.png)


![image_splash (1)](https://user-images.githubusercontent.com/102307675/171391462-6cd9b877-0c5a-401a-95a1-62940dd6b148.png)






======================================================================


[![image_splash](https://user-images.githubusercontent.com/102307675/171394681-093825c3-7a63-41d6-877e-9d319814bf8d.jpeg)](https://www.visionanalytics.ai)
## Artificial intelligence solutions to make easier your business using computer vision and data science

_Vision Analytics_, a startup dedicated to the development of artificial intelligence algorithms, especially computer vision.

Today, we generate millions of images per second. From these images, such as those captured by video surveillance cameras, very few of us extract any information.

The problem is therefore that images today in most cases are just a manual monitoring tool. You can't improve something that is not measured, and in the age of big data and AI, no business can afford to lose data.

At _Vision Analytics_, we use artificial neural networks capable of detecting almost any object in an image, in order to increase the security of spaces and at the same time extract data 

## What sets us apart from competitors in the area?

Other companies have few solutions, their algorithms are poor of precision and require hardware. We are far from all those situations.

Our data augmentation algorithms allow a single image to be multiplied by more than 50, making our neural networks robust with only a small dataset.

In addition, in most of cases we do not require additional hardware.
<div align="center">
  
![jetson-xavier-nx-dev-kit-2c50-D](https://user-images.githubusercontent.com/102307675/171668313-69941e24-091f-4c57-8752-c7e2e7b2b918.jpg) 

</div>

## But what happens if we simply want to evaluate small videos or images?

Our artificial intelligence can also be deployed on mobile devices. We have developed an application that, using the mobile phone's processor:

### -KUIKAI

<div align="center">


https://user-images.githubusercontent.com/102307675/171823834-d3f73521-5c41-4673-a507-72eaa48c67a4.mp4 
  
</div>


Runs our solutions without the need for internet access, all executed locally.

So the process of our services is very simple to explain: tell me what object you want to detect, give me access to the images and Vision Analytics will be able to train an AI capable of detecting almost any object and deploy the processing on the device of your choice. Once the object is detected, the post processing is as configurable as desired.

### We have achieved all this by developing artificial intelligence in an efficient, reliable and, above all, ethical way, since at Vision Analytics we detect any object while respecting privacy.


## Colaborations

<div align="center"> <img src="https://user-images.githubusercontent.com/102307675/171655108-d96f57cc-5539-49e2-aaac-a00099c4d681.png" width="" height="50"/> <img src="https://user-images.githubusercontent.com/102307675/171391462-6cd9b877-0c5a-401a-95a1-62940dd6b148.png")/></div>



