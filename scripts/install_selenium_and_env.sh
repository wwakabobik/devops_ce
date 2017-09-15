#!/bin/bash

echo "\r\nUpdating system ...\r\n"
sudo apt-get update


# Create folder to place selenium in
#
echo "\r\nCreating folder to place selenium in ...\r\n"
sudo mkdir ~/selenium
cd ~/selenium


# Get Selenium and install headless Java runtime
#
echo "\r\nInstalling Selenium and headless Java runtime ...\r\n"
sudo wget http://selenium-release.storage.googleapis.com/2.53/selenium-server-standalone-2.53.0.jar
sudo apt-get install openjdk-7-jre-headless -y


# Install Firefox
#
echo "\r\nInstalling Firefox ...\r\n"
sudo apt-get install firefox -y


# Install headless GUI for firefox.  'Xvfb is a display server that performs graphical operations in memory'
#
echo "\r\nInstalling XVFB (headless GUI for Firefox) ...\r\n"
sudo apt-get install xvfb -y


# Finally, starting up Selenium server
#
echo "\r\nStarting up Selenium server ...\r\n"
DISPLAY=:1 xvfb-run java -jar ~/selenium/selenium-server-standalone-2.53.0.jar
cd $HOME

# Install Maven
#
echo "\r\nInstalling Maven"
sudo apt-get install maven

# Install JUnit
# 
echo "\r\nInstalling JUnit"
sudo apt-get install junit

# Install Intellijidea
#
echo "\r\n\Installing Intellijidea...\r\n"
sudo wget https://www.jetbrains.com/idea/download/download-thanks.html?platform=linuxWithoutJDK&code=IIC
tar xfz ideaIC.tar.gz or ideaIU.tar.gz. ~/intellijidea
sudo tar xf -*.tar.gz -C /opt/
cd opt/-*/bin
sudo ./idea.sh
