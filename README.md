This repo is a proof of concept. It illustrates how to integrate pieces of the DevSecOps reference architecture into a project.

The goal of the DevSecOps reference architecture is to provide a recommendation for
- what tasks should be included in a best-practice, end-to-end workflow,
- what tools to use for each task, and
- how to integrate the various tools into an end-to-end workflow.

[Target reference architecture](https://pipelines.devops.aws.dev/application-pipeline/index.html) as defined by AWS.

[Reference architecture](https://docs.google.com/spreadsheets/d/1mb04C_vA5HBRHi3bxQocfi0fDhczAMsHRF2CLBZD05M/edit?usp=sharing) as implemented by this iteration of the POC.

[CircleCI workflow](https://app.circleci.com/pipelines/github/olukotun-ts/devops-box/55/workflows/68675db5-8a49-4112-9863-92986dd6fe88)

All environment variables are stored in the 'dobra' context.
- ARTIFACTORY_API_KEY
- ARTIFACTORY_URL
- ARTIFACTORY_USER
- AWS_DEFAULT_REGION
- AWS_ROLE_ARN_DEVOPSBOX_SERVERLESS: For OpenID Connect
- SNYK_TOKEN

Deployed to AWS Lambda, us-west-1. Application names:
- inventory-dev
- wishlist-dev

Monitoring by AWS CloudWatch, us-west-1. Log groups:
- /aws/lambda/inventory-dev-hello
- /aws/lambda/wishlist-dev-hello

Notes:
- Only two out of the three services are deployed (wishlist and inventory). The cart service isn't deployed because it is blocked by failing tests.
- The wishlist and inventory services don't have tests.