import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import internal.GlobalVariable
import groovy.json.JsonSlurper
import com.kms.katalon.core.util.KeywordUtil

// A. GET ALL USERS
def resAll = WS.sendRequest(findTestObject('Object Repository/A. GET ALL USERS'))
WS.verifyResponseStatusCode(resAll, 200)

// B. GET SINGLE USER -> ambil email
def resSingle = WS.sendRequest(findTestObject('Object Repository/B. GET SINGLE USER'))
WS.verifyResponseStatusCode(resSingle, 200)

def jsonSingle = new JsonSlurper().parseText(resSingle.getResponseBodyContent())
def email = jsonSingle?.email
if (!email) KeywordUtil.markFailedAndStop("Email kosong. Body: " + resSingle.getResponseBodyContent())

GlobalVariable.userEmail = email
KeywordUtil.logInfo("Email from GET single user = " + GlobalVariable.userEmail)

// C. PUT UPDATE
def resPut = WS.sendRequest(findTestObject('Object Repository/C. PUT UPDATE'))
WS.verifyResponseStatusCode(resPut, 200)

// D. POST REGISTER SUCCESSFUL (simulasi)
def resPost = WS.sendRequest(findTestObject('Object Repository/D. POST REGISTER SUCCESSFUL'))
WS.verifyResponseStatusCode(resPost, 201)
