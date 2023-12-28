import React, { useState } from 'react';
import './Fees.css'; 

interface FeesInfo {
  feeAmount: number;
  deadline: string;
}

const mockFeesInfo: FeesInfo = {
  feeAmount: 1500.00, 
  deadline: '2023-08-01T00:00:00Z', 
};

const Fees: React.FC = () => {
  const [feesInfo, setFeesInfo] = useState<FeesInfo>(mockFeesInfo);

  
  // useEffect(() => {
  // }, []);

  return (
    <div className="fees-container">
      <h1 className='exam-title'>Fees Page</h1>
      <div className="fees-info">
        <p>Fee Amount: ${feesInfo.feeAmount.toFixed(2)}</p>
        <p>Deadline: {new Date(feesInfo.deadline).toLocaleDateString()}</p>
      </div>
    </div>
  );
};

export default Fees;
