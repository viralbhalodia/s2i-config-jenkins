import jenkins.model.Jenkins
import java.util.logging.Level
import java.util.logging.Logger

final def LOG = Logger.getLogger("LABS")

def checkmarxBaseUrl = System.getenv('SLACK_BASE_URL') // TODO: update

if(checkmarxBaseUrl != null) {
  LOG.log(Level.INFO,  'Configuring checkmarx...' )

  def checkmarxCredentialId = "jenkins-git-password" // TODO: update

  def checkmarx = Jenkins.instance.getDescriptorByType(com.checkmarx.jenkins.CxScanBuilder.DescriptorImpl)

  checkmarx.serverUrl = checkmarxBaseUrl
  checkmarx.credentialsId = checkmarxCredentialId ? System.getenv('OPENSHIFT_BUILD_NAMESPACE') + "-" + checkmarxCredentialId : ''
  checkmarx.save()

  LOG.log(Level.INFO,  'Configured checkmarx' )
}
