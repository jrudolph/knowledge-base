# Cloud Batch Jobs

Sometimes you just need more compute resources to get some jobs done and just need the compute power to get the
job done. For these cases, it would be nice if there would be some cloud infrastructure to easily offload and run
those tasks.

Batch jobs are usually stateless, compute-heavy and often computed on some payload or input file.

Example batch jobs:

 * media files analysis (e.g. calculating fingerprints to find similar images in your photo collection)
 * transcoding of media files
 * regular data analysis based e.g. on big CSV files
 * machine learning tasks
 * any kind cron-job (though they are often not CPU-bound so you can get those usually done)

Often batch jobs are structured like this:

 * type of batch job ("extract video key frames", "downsize image")
 * parameters of the batch job ("target image size", "scaling algorithm")
 * parameters of the deployment (required amount of memory and CPU / GPU)
 * payload (e.g. file hash / location)
 * output file hash / location

Functionality of batch jobs would be represented by a docker image. Input data would often be most
usefully represented as an identifier like a URI or a hash if data is content-addressable.

Potential features:

 * automatic provisioning of resources - no machine setup required, just an API to deploy a load
 * autoscaling - you define resource limits but spinning up nodes etc based on load is automatic
 * automatic data handling - often several jobs need to be run on the same data or output data is to be processed
   further in another step, for that use case, data should be kept next to the compute resources, e.g. in form of
   a content-addressable cache
 * data encryption - data should be encrypted in-flight and at rest
 * log handling - logs per job should be accessible

A batch job often also accesses a DB for input data, these kind of jobs need access to internal infrastructure
and also often offload processing load to the DB so they are not easily moved into the cloud if other part of the
infrastructure is not already in the cloud as well.

The current term for these kinds of services seems to be "serverless container platform" in its generic form.

Following are some current option. Docker-based deployment and autoscaling should work fine. Currently, data management
is an aspect that the user has to handle on their own, i.e. a batch job needs to download the payload and upload the result
caring for aspects as locality and caching, and encryption themselves.

## AWS fargate

[Homepage](https://aws.amazon.com/fargate/)

From the [FAQ](https://aws.amazon.com/fargate/faqs/?nc=sn&loc=4):

> Q: What use cases does AWS Fargate support?
>
> AWS Fargate supports all of the common container use cases, for example microservices architecture applications, batch processing, machine learning applications, and migrating on premise applications to the cloud.

## iron.io IronWorker

[Homepage](https://dev.iron.io/worker/getting_started/)

## Google Cloud Run

[Homepage](https://cloud.google.com/run)

## Azure Equivalent

To-be-researched