pipeline
{
agent any

stages
{
stage('Build')
{
steps{
bat "mvn clean"
}
}
stage('deploy')
{
steps{
echo "deploying the code" 
}
}
stage('Test')
{
steps{
bat "mvn test"
}
}
stage('Unit testing')
{
steps{
bat "mvn compile"

}
}
stage('Release')
{
steps{
echo "Releasing the project"
}
}


}




}